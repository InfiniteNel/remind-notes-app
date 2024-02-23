package jroslar.infinitenel.remindnotes.domain.usecase

import jroslar.infinitenel.remindnotes.data.repository.NoteRepositoryImpl
import jroslar.infinitenel.remindnotes.domain.model.NoteModel
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl
) {
    suspend operator fun invoke(noteModel: NoteModel) =
        noteRepositoryImpl.insertNote(noteModel)
}