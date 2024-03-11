package jroslar.infinitenel.remindnotes.domain.model

data class ReminderModel(
    override val title: String,
    override val description: String,
    override val important: Boolean,
    val remindDay: String,
    val repeatDay: List<String>,
    val timeDay: String
): NotificationModel
