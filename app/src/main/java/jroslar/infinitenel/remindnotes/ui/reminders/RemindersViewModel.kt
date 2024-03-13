package jroslar.infinitenel.remindnotes.ui.reminders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jroslar.infinitenel.remindnotes.domain.usecase.GetAllRemindersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val getAllRemindersUseCase: GetAllRemindersUseCase
): ViewModel() {

    private var _state = MutableStateFlow<RemindersState>(RemindersState.NoData(emptyList()))
    val state: StateFlow<RemindersState> = _state

    fun getRemindersList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { getAllRemindersUseCase() }

            if (result.isEmpty()) {
                _state.value = RemindersState.NoData(emptyList())
            } else {
                _state.value = RemindersState.Success(result)
            }
        }
    }
}