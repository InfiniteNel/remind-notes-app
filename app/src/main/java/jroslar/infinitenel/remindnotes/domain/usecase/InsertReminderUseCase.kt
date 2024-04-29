package jroslar.infinitenel.remindnotes.domain.usecase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jroslar.infinitenel.remindnotes.core.extensions.dateToMillisecondes
import jroslar.infinitenel.remindnotes.core.extensions.timeToMillisecondes
import jroslar.infinitenel.remindnotes.core.utils.NotificationUtils
import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import javax.inject.Inject

class InsertReminderUseCase @Inject constructor(
    private val repositoryImpl: ReminderRepositoryImpl,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(reminderModel: ReminderModel) {
        val reminderId = repositoryImpl.insertReminder(reminderModel).toInt()
        if (reminderId != -1) {
            NotificationUtils.createScheduledNotification(
                context,
                reminderModel.remindDay.dateToMillisecondes() + reminderModel.timeDay.timeToMillisecondes(),
                reminderId,
                reminderModel.title,
                reminderModel.description
            )
        }
    }
}