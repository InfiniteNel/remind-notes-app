package jroslar.infinitenel.remindnotes.domain.model

import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity

data class NoteModel (
    override val title: String,
    override val description: String,
    override val important: Boolean = false,
    val noteDay: String
): NotificationModel

fun NoteModel.toEntityNote(): NoteEntity = NoteEntity(0, title, description, important, noteDay)