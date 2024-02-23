package jroslar.infinitenel.remindnotes.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jroslar.infinitenel.remindnotes.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<NotesState>(NotesState.NoData(emptyList()))
    val state: StateFlow<NotesState> = _state

    fun getListNotes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { getAllNotesUseCase() }

            if (result.isNotEmpty())
                _state.value = NotesState.Success(result)
        }
    }
}