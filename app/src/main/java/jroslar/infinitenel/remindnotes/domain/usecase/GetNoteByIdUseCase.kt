package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.NoteRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.NoteModel
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repositoryImpl: NoteRepositoryImpl
) {
    suspend operator fun invoke(id: Int): NoteModel =
        repositoryImpl.getNoteById(id)
}