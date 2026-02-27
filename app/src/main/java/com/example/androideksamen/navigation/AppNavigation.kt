package com.example.androideksamen.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*

import com.example.androideksamen.ui.screens.*

/*
  Here is the navigation setup
  this:
  Creates and holds NavigationController
  connetcts screens to routes
  handle arguments passing between them
*/

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        // defines all navigation destinations
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.padding(padding)
        ) {

            composable("detail/{id}") { backStackEntry ->

                val id =
                    backStackEntry.arguments
                        ?.getString("id")
                        ?.toIntOrNull()

                id?.let {
                    DetailScreen(navController, it)
                }
            }
           // the different screens
            composable("list") {
                CharacterListScreen(navController)
            }

            composable("create") {
                CreateCharacterScreen(navController)
            }
            composable("saved") {
                SavedCharactersScreen(navController)
            }
            composable("edit/{id}") { backStackEntry ->

                val id =
                    backStackEntry.arguments
                        ?.getString("id")
                        ?.toIntOrNull()

                id?.let {
                    EditCharacterScreen(navController, it)
                }
            }
        }
    }
}