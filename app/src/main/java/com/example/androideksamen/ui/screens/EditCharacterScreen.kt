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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androideksamen.components.MessageBox
import com.example.androideksamen.components.MessageType
import com.example.androideksamen.viewmodel.EditCharacterViewModel
import com.example.androideksamen.ui.components.CharacterCard
import com.example.androideksamen.ui.components.CharacterCardMode
import com.example.androideksamen.ui.components.Header

/*
  screen for editing and deleting saved characters
  gets characters from local database
 */

@Composable
fun EditCharacterScreen(
    navController: NavController,
    characterId: Int
) {

    val viewModel: EditCharacterViewModel = viewModel()

    Scaffold(
        topBar = {
            Header(
                title = "Edit Character",
                showBack = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->

        LaunchedEffect(characterId) {
            viewModel.loadCharacter(characterId)
        }

        val character = viewModel.character

        if (character == null) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {


                viewModel.message?.let {
                    MessageBox(
                        message = it,
                        type = MessageType.SUCCESS
                    )                }

                //   current character before editing
                CharacterCard(
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    image = character.image,
                    mode = CharacterCardMode.PREVIEW,
                    onCardClick = {}
                )

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor =
                        MaterialTheme.colorScheme.surfaceContainerLowest
                    ),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        OutlinedTextField(
                            value = character.name,
                            onValueChange = viewModel::updateName,
                            label = { Text("Name") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor =
                                MaterialTheme.colorScheme.surfaceContainerHigh
                            )
                        )

                        OutlinedTextField(
                            value = character.status,
                            onValueChange = viewModel::updateStatus,
                            label = { Text("Status") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor =
                                MaterialTheme.colorScheme.surfaceBright
                            )
                        )

                        OutlinedTextField(
                            value = character.species,
                            onValueChange = viewModel::updateSpecies,
                            label = { Text("Species") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor =
                                MaterialTheme.colorScheme.surfaceBright
                            )
                        )

                        OutlinedTextField(
                            value = character.gender,
                            onValueChange = viewModel::updateGender,
                            label = { Text("Gender") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor =
                                MaterialTheme.colorScheme.surfaceBright
                            )
                        )
                    }
                }

                //  updates with message after
                Button(
                    onClick = { viewModel.updateCharacter() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text("Update Character")
                }

                Button(
                    onClick = {
                        viewModel.deleteCharacter()
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text("Delete Character")
                }

            }
        }
    }
}