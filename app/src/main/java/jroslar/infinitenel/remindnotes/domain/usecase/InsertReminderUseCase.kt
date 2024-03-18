package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import javax.inject.Inject

class InsertReminderUseCase @Inject constructor(
    private val repositoryImpl: ReminderRepositoryImpl
) {
    suspend operator fun invoke(reminderModel: ReminderModel) =
        repositoryImpl.insertReminder(reminderModel)
}