@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androideksamen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.androideksamen.viewmodel.CharacterViewModel
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.layout.ContentScale
import com.example.androideksamen.ui.components.Header

/*
  detail screen more information about the selected character
  navigates to character via ID
 */


@Composable
fun DetailScreen(
    navController: NavController,
    characterId: Int
) {

    val viewModel: CharacterViewModel = viewModel()

    // fetch character data when navigates to the screen by character ID
    LaunchedEffect(characterId) {
        viewModel.fetchCharacterById(characterId)
    }

    val character = viewModel.selectedCharacter
    val isLoading = viewModel.isLoading
    Scaffold(
        topBar = {
            Header( title = "Details",
                showBack = true,
                onBackClick = { navController.popBackStack() }
            )
        }

    ) { paddingValues ->

        if (character == null) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
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
            ) {


                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(310.dp)
,
                    contentScale = ContentScale.Crop
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                    ),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {

                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        DetailRow("Status", character.status)
                        DetailRow("Species", character.species)
                        DetailRow("Gender", character.gender)
                        DetailRow("Origin", character.origin["name"] ?: "Unknown")
                        DetailRow("Location", character.location["name"] ?: "Unknown")
                        DetailRow("Episodes", character.episode.size.toString())

                        Spacer(Modifier.height(8.dp))

                    }
                }
            }

        }
    }
}
@Composable
fun DetailRow(label: String, value: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}