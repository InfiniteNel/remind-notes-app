package jroslar.infinitenel.remindnotes.domain.repository

import jroslar.infinitenel.remindnotes.domain.model.NoteModel

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteModel>
    suspend fun insertNote(noteModel: NoteModel)
}