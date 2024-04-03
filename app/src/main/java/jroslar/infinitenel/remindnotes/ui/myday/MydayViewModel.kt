package jroslar.infinitenel.remindnotes.ui.myday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jroslar.infinitenel.remindnotes.data.repository.NotificationRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MydayViewModel @Inject constructor(
    private val notificationRepositoryImpl: NotificationRepositoryImpl
) : ViewModel() {

    private var _state = MutableStateFlow<MydayState>(MydayState.Loading)
    val state: StateFlow<MydayState> = _state

    fun getListNotification(dayToday: String, dayTomorrow: String) {
        viewModelScope.launch {
            val resultToday = withContext(Dispatchers.IO) {
                notificationRepositoryImpl.getListNotificationByDay(dayToday)
            }
            val resultTomorrow = withContext(Dispatchers.IO) {
                notificationRepositoryImpl.getListNotificationByDay(dayTomorrow)
            }

            _state.value = MydayState.Success(Pair(resultToday, resultTomorrow))
        }
    }
}