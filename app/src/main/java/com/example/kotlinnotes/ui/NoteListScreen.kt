package com.example.kotlinnotes.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.kotlinnotes.data.Note
import com.example.kotlinnotes.viewmodel.NoteViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun NoteListScreen(viewModel: NoteViewModel, navController: NavController) {
    val notes: List<Note> by viewModel.notes.observeAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("detail") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(notes) { note: Note ->
                NoteItem(
                    note = note,
                    onClick = { navController.navigate("detail/${note.id}") },
                    onDelete = { viewModel.deleteNote(note) } // fixed
                )
            }
        }
    }
}