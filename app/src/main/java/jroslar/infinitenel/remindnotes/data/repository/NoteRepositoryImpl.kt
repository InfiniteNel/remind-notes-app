package jroslar.infinitenel.remindnotes.data.repository

import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.database.entities.toModelNote
import jroslar.infinitenel.remindnotes.domain.repository.NoteRepository
import jroslar.infinitenel.remindnotes.domain.model.NoteModel
import jroslar.infinitenel.remindnotes.domain.model.toEntityNote
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
): NoteRepository {
    override suspend fun getAllNotes(): List<NoteModel> {
        return dao.getAllNotes().map { it.toModelNote() }
    }

    override suspend fun getNoteById(id: Int): NoteModel {
        return dao.getNoteById(id).toModelNote()
    }

    override suspend fun insertNote(noteModel: NoteModel) {
        dao.insertNote(noteModel.toEntityNote())
    }

    override suspend fun deleteNote(id: Int) {
        dao.deleteNote(id)
    }
}