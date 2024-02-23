package jroslar.infinitenel.remindnotes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY noteDay ASC")
    suspend fun getAllNotes(): List<NoteEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteEntity: NoteEntity)
}