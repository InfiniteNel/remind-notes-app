package jroslar.infinitenel.remindnotes.ui.notes

import jroslar.infinitenel.remindnotes.domain.model.NoteModel

sealed class NotesState {
    data class NoData(val noteList: List<NoteModel>): NotesState()
    data class Success(val noteList: List<NoteModel>): NotesState()
}