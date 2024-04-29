package jroslar.infinitenel.remindnotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.database.dao.ReminderDao
import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity
import jroslar.infinitenel.remindnotes.data.database.entities.ReminderEntity

@Database(entities = [NoteEntity::class, ReminderEntity::class], version = 3)
@TypeConverters(ConverterDaysOfWeek::class)
abstract class NotificationDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getReminderDao(): ReminderDao
}