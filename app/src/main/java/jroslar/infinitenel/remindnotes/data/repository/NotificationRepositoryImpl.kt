package jroslar.infinitenel.remindnotes.data.repository

import android.os.Build
import jroslar.infinitenel.remindnotes.core.Constant
import jroslar.infinitenel.remindnotes.core.extensions.dateToMillisecondes
import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.database.dao.ReminderDao
import jroslar.infinitenel.remindnotes.data.database.entities.toModelNote
import jroslar.infinitenel.remindnotes.data.database.entities.toModelReminder
import jroslar.infinitenel.remindnotes.domain.model.NotificationModel
import jroslar.infinitenel.remindnotes.domain.repository.NotificationRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val reminderDao: ReminderDao
) : NotificationRepository {
    override suspend fun getListNotificationByDay(date: String): List<NotificationModel> {
        val result: MutableList<NotificationModel> = mutableListOf()

        result.addAll(noteDao.getNotesByDay(date.dateToMillisecondes()).map { it.toModelNote() })
        result.addAll(reminderDao.getRemindersByDay(date.dateToMillisecondes()).map { it.toModelReminder() })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(Constant.FORMAT_DATE_OVER_API_26, Locale.getDefault())
            val data = LocalDate.parse(date, formatter)

            result.addAll(
                reminderDao.getRemindersByDayOfWeek(data.dayOfWeek.toString())
                    .map { it.toModelReminder() }
                    .filter { it.remindDay.isEmpty() }
            )
        }

        return result
    }
}