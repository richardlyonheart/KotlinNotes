package com.example.kotlinnotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
//creating a room entity and putting it into a table called notes
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)