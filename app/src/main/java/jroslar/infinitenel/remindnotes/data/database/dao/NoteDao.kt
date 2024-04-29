package jroslar.infinitenel.remindnotes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY noteDay ASC")
    suspend fun getAllNotes(): List<NoteEntity>
    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity
    @Query("SELECT * FROM note_table WHERE noteDay = :day")
    suspend fun getNotesByDay(day: Long): List<NoteEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteEntity: NoteEntity)
    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteNote(id: Int)
    @Update
    suspend fun updateNote(noteEntity: NoteEntity)
}