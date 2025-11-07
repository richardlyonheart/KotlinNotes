package com.example.kotlinnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase

//database for notes app
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    //abstract function to get the dao
    abstract fun noteDao(): NoteDao
}

