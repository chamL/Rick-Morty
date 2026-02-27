@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androideksamen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androideksamen.ui.components.CharacterInputField
import com.example.androideksamen.ui.components.Header
import com.example.androideksamen.viewmodel.CreateCharacterViewModel

/*
 screen for creating a character using the inputs
 with a snackbar feedback
 navigate back after creating charecter
 */

@Composable
fun CreateCharacterScreen(navController: NavController) {

    val viewModel: CreateCharacterViewModel = viewModel()
    val snackbarHostState = remember { SnackbarHostState() }

    // message and goes back when character is created
    LaunchedEffect(viewModel.savedMessage) {
        viewModel.savedMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessage()
            navController.popBackStack() // ← går tilbake etter save
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Header(
                title = "Create Character",
                showBack = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState()),
                colors = CardDefaults.cardColors(
                    containerColor =
                    MaterialTheme.colorScheme.surfaceContainerLowest
                ),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(22.dp)
            ) {

                Column(
                    modifier = Modifier.padding(22.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    CharacterInputField(
                        value = viewModel.name,
                        label = "Name"
                    ) { viewModel.name = it }

                    CharacterInputField(
                        value = viewModel.status,
                        label = "Status"
                    ) { viewModel.status = it }

                    CharacterInputField(
                        value = viewModel.species,
                        label = "Species"
                    ) { viewModel.species = it }

                    CharacterInputField(
                        value = viewModel.gender,
                        label = "Gender"
                    ) { viewModel.gender = it }

                    Spacer(Modifier.height(6.dp))

                    Button(
                        onClick = { viewModel.saveCharacter() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Save Character")
                    }

                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Back")
                    }
                }
            }
        }
    }
}