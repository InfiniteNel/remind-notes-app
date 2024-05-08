package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import javax.inject.Inject

class GetReminderById @Inject constructor(
    private val repositoryImpl: ReminderRepositoryImpl
) {
    suspend operator fun invoke(id: Int): ReminderModel =
        repositoryImpl.getReminderById(id)
}