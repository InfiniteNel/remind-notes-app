package jroslar.infinitenel.remindnotes.data.repository

import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.database.dao.ReminderDao
import jroslar.infinitenel.remindnotes.data.database.entities.toModelNote
import jroslar.infinitenel.remindnotes.data.database.entities.toModelReminder
import jroslar.infinitenel.remindnotes.domain.model.NotificationModel
import jroslar.infinitenel.remindnotes.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val reminderDao: ReminderDao
): NotificationRepository {
    override suspend fun getListNotificationByDay(date: String): List<NotificationModel> {
        val result: MutableList<NotificationModel> = mutableListOf()

        result.addAll(noteDao.getNotesByDay(date).map { it.toModelNote() })
        result.addAll(reminderDao.getRemindersByDay(date).map { it.toModelReminder() })

        return result
    }
}