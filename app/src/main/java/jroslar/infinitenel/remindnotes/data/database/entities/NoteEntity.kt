package jroslar.infinitenel.remindnotes.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jroslar.infinitenel.remindnotes.domain.model.NoteModel

@Entity(tableName = "note_table")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "important") val important: Boolean,
    @ColumnInfo(name = "noteDay") val noteDay: String
)

fun NoteEntity.toModelNote(): NoteModel = NoteModel(id, title, description, important, noteDay)