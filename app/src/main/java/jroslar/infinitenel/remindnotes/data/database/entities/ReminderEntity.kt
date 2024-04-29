package jroslar.infinitenel.remindnotes.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import jroslar.infinitenel.remindnotes.core.utils.DaysOfWeek
import jroslar.infinitenel.remindnotes.data.database.ConverterDaysOfWeek
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel

@Entity(tableName = "reminder_table")
data class ReminderEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "important") val important: Boolean,
    @ColumnInfo(name = "remindDay") val remindDay: String,
    @ColumnInfo(name = "repeatDay") @TypeConverters(ConverterDaysOfWeek::class) val repeatDay: List<DaysOfWeek>,
    @ColumnInfo(name = "timeDay") val timeDay: String
)

fun ReminderEntity.toModelReminder(): ReminderModel = ReminderModel(id, title, description, important, remindDay, repeatDay, timeDay)