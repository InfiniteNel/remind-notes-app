package jroslar.infinitenel.remindnotes.data.repository

import jroslar.infinitenel.remindnotes.data.database.dao.ReminderDao
import jroslar.infinitenel.remindnotes.data.database.entities.toModelReminder
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import jroslar.infinitenel.remindnotes.domain.model.toEntityReminder
import jroslar.infinitenel.remindnotes.domain.repository.ReminderRepository
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val dao: ReminderDao
) : ReminderRepository {
    override suspend fun getAllReminders(): List<ReminderModel> =
        dao.getAllReminders().map { it.toModelReminder() }

    override suspend fun insertReminder(reminderModel: ReminderModel) =
        dao.insertReminder(reminderModel.toEntityReminder())

    override suspend fun deleteReminder(id: Int) {
        dao.deleteReminder(id)
    }

    override suspend fun updateReminder(reminderModel: ReminderModel) {
        dao.updateReminder(reminderModel.toEntityReminder())
    }
}