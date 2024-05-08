package jroslar.infinitenel.remindnotes.domain.repository

import jroslar.infinitenel.remindnotes.domain.model.ReminderModel

interface ReminderRepository {
    suspend fun getAllReminders(): List<ReminderModel>
    suspend fun getReminderById(id: Int): ReminderModel
    suspend fun insertReminder(reminderModel: ReminderModel): Long
    suspend fun deleteReminder(id: Int)
    suspend fun updateReminder(reminderModel: ReminderModel)
}