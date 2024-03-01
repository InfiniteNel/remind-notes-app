package jroslar.infinitenel.remindnotes.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jroslar.infinitenel.remindnotes.data.database.entities.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY noteDay ASC")
    suspend fun getAllNotes(): List<NoteEntity>
    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteEntity: NoteEntity)
    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}