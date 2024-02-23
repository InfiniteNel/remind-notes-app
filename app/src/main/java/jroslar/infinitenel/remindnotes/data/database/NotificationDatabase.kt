package jroslar.infinitenel.remindnotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotificationDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}