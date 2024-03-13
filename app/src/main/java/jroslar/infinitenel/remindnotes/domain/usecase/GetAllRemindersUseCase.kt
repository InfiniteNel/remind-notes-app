package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import javax.inject.Inject

class GetAllRemindersUseCase @Inject constructor(
    private val reminderRepositoryImpl: ReminderRepositoryImpl
) {
    suspend operator fun invoke(): List<ReminderModel> =
        reminderRepositoryImpl.getAllReminders()
}