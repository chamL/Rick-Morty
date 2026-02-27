@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androideksamen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androideksamen.components.LoadingState
import com.example.androideksamen.components.MessageBox
import com.example.androideksamen.components.MessageType
import com.example.androideksamen.ui.components.CharacterCard
import com.example.androideksamen.ui.components.CharacterCardMode
import com.example.androideksamen.ui.components.Header
import com.example.androideksamen.ui.components.SearchBar
import com.example.androideksamen.ui.components.StatusFilterRow
import com.example.androideksamen.viewmodel.CharacterViewModel

/*
 main screen shows the list of the characters
 handle navigation to the detailscreen
 handling search and filtering of the list
 */

@Composable
fun CharacterListScreen(navController: NavController) {

    val viewModel: CharacterViewModel = viewModel()

    // Snackbar used to display save message
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.savedMessage) {
        viewModel.savedMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearMessage()
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Header(title = "Rick & Morty Characters")
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            SearchBar(
                value = viewModel.searchQuery,
                onValueChange = viewModel::onSearchChange
            )

            StatusFilterRow(viewModel)

            if (viewModel.isLoading) {
                LoadingState()
            }

            viewModel.errorMessage?.let { error ->
                MessageBox(
                    message = error,
                    type = MessageType.ERROR
                )
            }

            if (!viewModel.isLoading && viewModel.errorMessage == null) {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 22.dp)
                ) {

                    items(viewModel.filteredCharacters) { character ->

                        CharacterCard(
                            name = character.name,
                            status = character.status,
                            species = character.species,
                            gender = character.gender,
                            image = character.image,
                            mode = CharacterCardMode.SAVE,
                            onCardClick = {
                                navController.navigate("detail/${character.id}")
                            },
                            onPrimaryAction = {
                                viewModel.saveCharacter(character)
                            }
                        )
                    }
                }
            }
        }
    }
}