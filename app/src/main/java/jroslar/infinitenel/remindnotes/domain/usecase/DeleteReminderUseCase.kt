package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import javax.inject.Inject

class DeleteReminderUseCase @Inject constructor(
    private val repositoryImpl: ReminderRepositoryImpl
) {
    suspend operator fun invoke(id: Int) =
        repositoryImpl.deleteReminder(id)
}