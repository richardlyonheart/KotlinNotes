package com.example.kotlinnotes.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
/*
 Data Access Object (DAO) for the Note entity.
 Defines all database operations related to notes.
 Room will generate the implementation at compile time.
 */

@Dao
interface NoteDao {
    // Retrieve all notes ordered by timestamp in descending order
    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>>

    // Retrieve a single note by its ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    // Update an existing note
    @Update
    suspend fun update(note: Note)
    
    // Delete a note
    @Delete
    suspend fun delete(note: Note)
}
