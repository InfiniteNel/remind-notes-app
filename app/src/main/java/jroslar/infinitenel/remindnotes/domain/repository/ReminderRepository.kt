package jroslar.infinitenel.remindnotes.domain.repository

import jroslar.infinitenel.remindnotes.domain.model.ReminderModel

interface ReminderRepository {
    suspend fun getAllReminders(): List<ReminderModel>
    suspend fun insertReminder(reminderModel: ReminderModel)
    suspend fun deleteReminder(id: Int)
    suspend fun updateReminder(reminderModel: ReminderModel)
}