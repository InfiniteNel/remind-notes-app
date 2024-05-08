package jroslar.infinitenel.remindnotes.domain.usecase

import android.content.Context
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import jroslar.infinitenel.remindnotes.core.extensions.dateToMillisecondes
import jroslar.infinitenel.remindnotes.core.extensions.timeToMillisecondes
import jroslar.infinitenel.remindnotes.core.utils.DateUtils
import jroslar.infinitenel.remindnotes.core.utils.DaysOfWeek
import jroslar.infinitenel.remindnotes.core.utils.NotificationUtils
import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import java.time.LocalDate
import javax.inject.Inject

class InsertReminderUseCase @Inject constructor(
    private val repositoryImpl: ReminderRepositoryImpl,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(reminderModel: ReminderModel) {
        val reminderId = repositoryImpl.insertReminder(reminderModel).toInt()
        if (reminderId != -1) {
            createScheduledNotification(context, reminderModel.copy(id = reminderId))
        }
    }

    private fun createScheduledNotification(
        context: Context,
        reminderModel: ReminderModel
    ) {
        if (reminderModel.remindDay.isEmpty() &&
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val repeatDay: Int
            val nextDayOfWeek = DaysOfWeek.valueOf(LocalDate.now().dayOfWeek.toString())
            val indexDay = reminderModel.repeatDay.indexOf(nextDayOfWeek)

            repeatDay = if (indexDay == -1) {
                val nextDay = reminderModel.repeatDay.firstOrNull { it != nextDayOfWeek }
                reminderModel.repeatDay.indexOf(nextDay)
            } else {
                indexDay + 1 % reminderModel.repeatDay.size
            }

            val nextDate = DateUtils.getNextDateOfDayOfWeek(reminderModel.repeatDay[repeatDay])

            NotificationUtils.createScheduledNotification(
                context,
                nextDate + reminderModel.timeDay.timeToMillisecondes(),
                reminderModel.id
            )
        } else if (reminderModel.remindDay.isNotEmpty()) {
            NotificationUtils.createScheduledNotification(
                context,
                reminderModel.remindDay.dateToMillisecondes() + reminderModel.timeDay.timeToMillisecondes(),
                reminderModel.id
            )
        }
    }
}