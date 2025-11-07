package com.example.kotlinnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinnotes.ui.NoteListScreen
import com.example.kotlinnotes.ui.NoteDetailScreen
import com.example.kotlinnotes.ui.theme.KotlinNotesTheme
import com.example.kotlinnotes.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ViewModel scoped to this Activity
        val viewModel: NoteViewModel by viewModels()

        setContent {
            KotlinNotesTheme {
                AppNavigation(viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(viewModel, navController)
        }
        composable("detail") {
            NoteDetailScreen(viewModel, navController, null)
        }
        composable("detail/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            NoteDetailScreen(viewModel, navController, noteId)
        }
    }
}