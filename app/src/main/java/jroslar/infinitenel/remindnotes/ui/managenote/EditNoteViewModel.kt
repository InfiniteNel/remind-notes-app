package jroslar.infinitenel.remindnotes.ui.managenote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jroslar.infinitenel.remindnotes.domain.model.NoteModel
import jroslar.infinitenel.remindnotes.domain.usecase.DeleteNoteUseCase
import jroslar.infinitenel.remindnotes.domain.usecase.GetNoteByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {
    private var _data = MutableStateFlow<NoteModel?>(null)
    val data: StateFlow<NoteModel?> = _data

    fun getNote(id: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { getNoteByIdUseCase(id) }

            _data.value = result
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteNoteUseCase(id)
            }
        }
    }
}