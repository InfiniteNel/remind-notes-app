package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.NoteRepositoryImpl
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl
) {
    suspend operator fun invoke(id: Int) =
        noteRepositoryImpl.deleteNote(id)
}