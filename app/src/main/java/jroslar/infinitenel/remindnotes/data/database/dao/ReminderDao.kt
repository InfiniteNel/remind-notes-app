package jroslar.infinitenel.remindnotes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import jroslar.infinitenel.remindnotes.data.database.entities.ReminderEntity

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder_table ORDER BY remindDay ASC")
    suspend fun getAllReminders(): List<ReminderEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReminder(reminderEntity: ReminderEntity)
    @Query("DELETE FROM reminder_table WHERE id = :id")
    suspend fun deleteReminder(id: Int)
    @Update
    suspend fun updateReminder(reminderEntity: ReminderEntity)
}