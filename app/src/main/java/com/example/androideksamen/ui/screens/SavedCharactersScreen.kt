@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androideksamen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androideksamen.viewmodel.SavedCharactersViewModel
import com.example.androideksamen.ui.components.CharacterCard
import com.example.androideksamen.ui.components.CharacterCardMode
import com.example.androideksamen.ui.components.Header
import com.example.androideksamen.components.EmptyState

/*
  screen for the saved and created characters in the database
  handle delete with an feedback message
 */

@Composable
fun SavedCharactersScreen(navController: NavController) {

    val viewModel: SavedCharactersViewModel = viewModel()
    val characters by viewModel.characters.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.message) {
        viewModel.message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessage()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Header(
                title = "Saved Characters",
                showBack = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            //  empty state it there is nothing to show
            if (characters.isEmpty()) {

                EmptyState()

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 8.dp,
                        bottom = 24.dp
                    )
                ) {

                    items(
                        items = characters,
                        key = { it.id }
                    ) { character ->

                        CharacterCard(
                            name = character.name,
                            status = character.status,
                            species = character.species,
                            gender = character.gender,
                            image = character.image,
                            mode = CharacterCardMode.REMOVE,
                            onCardClick = {
                                navController.navigate("edit/${character.id}")
                            },
                            onPrimaryAction = {
                                viewModel.deleteCharacter(character)
                            }
                        )
                    }
                }
            }
        }
    }
}