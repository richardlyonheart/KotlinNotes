package com.example.kotlinnotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kotlinnotes.data.Note
import androidx.compose.runtime.livedata.observeAsState
import com.example.kotlinnotes.viewmodel.NoteViewModel

@Composable
fun NoteDetailScreen(viewModel: NoteViewModel, navController: NavController, noteId: Int?) {
    val notes: List<Note> by viewModel.notes.observeAsState(initial = emptyList())
    val existingNote = notes.find { it.id == noteId }

    var title by remember { mutableStateOf(existingNote?.title ?: "") }
    var content by remember { mutableStateOf(existingNote?.content ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (existingNote != null) {
                // Update existing note
                viewModel.updateNote(existingNote.copy(title = title, content = content))
            } else {
                // Add new note
                viewModel.addNote(title, content)
            }
            navController.popBackStack()
        }) {
            Text(if (existingNote != null) "Update" else "Save")
        }
    }}