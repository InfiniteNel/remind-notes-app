package jroslar.infinitenel.remindnotes.domain.model

sealed interface NotificationModel {
    val title: String
    val description: String
    val important: Boolean
}