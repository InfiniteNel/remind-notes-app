package jroslar.infinitenel.remindnotes.ui.managenote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jroslar.infinitenel.remindnotes.domain.model.NoteModel
import jroslar.infinitenel.remindnotes.domain.usecase.InsertNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ManageNoteViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase
): ViewModel() {

    fun insertNote(noteModel: NoteModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertNoteUseCase(noteModel)
            }
        }
    }
}