package com.example.kotlinnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.kotlinnotes.data.Note
import com.example.kotlinnotes.data.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        "note_db"
    ).build()

    val notes = db.noteDao().getAllNotes().asLiveData()

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            db.noteDao().insert(Note(title = title, content = content))
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            db.noteDao().update(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            db.noteDao().delete(note)
        }
    }
}