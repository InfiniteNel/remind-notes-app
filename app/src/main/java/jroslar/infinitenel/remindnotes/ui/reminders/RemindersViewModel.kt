package jroslar.infinitenel.remindnotes.ui.reminders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import jroslar.infinitenel.remindnotes.domain.usecase.DeleteReminderUseCase
import jroslar.infinitenel.remindnotes.domain.usecase.GetAllRemindersUseCase
import jroslar.infinitenel.remindnotes.domain.usecase.InsertReminderUseCase
import jroslar.infinitenel.remindnotes.domain.usecase.UpdateReminderUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val getAllRemindersUseCase: GetAllRemindersUseCase,
    private val deleteReminderUseCase: DeleteReminderUseCase,
    private val updateReminderUseCase: UpdateReminderUseCase,
    private val insertReminderUseCase: InsertReminderUseCase
): ViewModel() {

    private var _state = MutableStateFlow<RemindersState>(RemindersState.NoData(emptyList()))
    val state: StateFlow<RemindersState> = _state

    fun getRemindersList() {
        viewModelScope.launch {
            changeStateList(
                withContext(Dispatchers.IO) { getAllRemindersUseCase() }
            )
        }
    }

    fun deleteReminder(id: Int) {
        viewModelScope.launch {
            changeStateList(
                withContext(Dispatchers.IO) {
                    deleteReminderUseCase(id)
                    getAllRemindersUseCase()
                }
            )
        }
    }

    fun updateReminder(reminderModel: ReminderModel) {
        viewModelScope.launch {
            changeStateList(
                withContext(Dispatchers.IO) {
                    updateReminderUseCase(reminderModel)
                    getAllRemindersUseCase()
                }
            )
        }
    }

    fun insertReminder(reminderModel: ReminderModel) {
        viewModelScope.launch {
            changeStateList(
                withContext(Dispatchers.IO) {
                    insertReminderUseCase(reminderModel)
                    getAllRemindersUseCase()
                }
            )
        }
    }

    private fun changeStateList(result: List<ReminderModel>) {
        if (result.isEmpty()) {
            _state.value = RemindersState.NoData(emptyList())
        } else {
            _state.value = RemindersState.Success(result)
        }
    }
}