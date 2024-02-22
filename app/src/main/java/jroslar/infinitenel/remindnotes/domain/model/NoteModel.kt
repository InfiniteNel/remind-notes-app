package jroslar.infinitenel.remindnotes.domain.model

data class NoteModel (
    override val title: String,
    override val description: String,
    override val important: Boolean = false,
    val noteDay: String
): NotificationModel