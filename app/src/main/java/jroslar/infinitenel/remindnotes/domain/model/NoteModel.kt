package jroslar.infinitenel.remindnotes.domain.model

import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity

data class NoteModel (
    val id: Int = 0,
    override val title: String,
    override val description: String,
    override val important: Boolean = false,
    val noteDay: String
): NotificationModel

fun NoteModel.toEntityNote(): NoteEntity = NoteEntity(id, title, description, important, noteDay)