package jroslar.infinitenel.remindnotes.ui.myday

import jroslar.infinitenel.remindnotes.domain.model.NotificationModel

sealed class MydayState {
    data object Loading : MydayState()
    data class Success(val list: Pair<List<NotificationModel>, List<NotificationModel>>) :
        MydayState()
}