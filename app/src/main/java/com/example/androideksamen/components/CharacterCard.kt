package com.example.androideksamen.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage

/*
 Component that i reuse in the project,
flexible where i can add save, remove, edit and a clean preview
(conrolled på CharacterCardMode)
 */

enum class CharacterCardMode {
    SAVE,
    REMOVE,
    EDIT,
    PREVIEW
}

@Composable
fun CharacterCard(
    name: String,
    status: String,
    species: String,
    gender: String,
    image: String,
    mode: CharacterCardMode,
    onCardClick: () -> Unit,
    onPrimaryAction: () -> Unit = {},
    onSecondaryAction: (() -> Unit)? = null
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = onCardClick
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {

            AsyncImage(
                model = image,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(110.dp)
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text("Status: $status")
                Text("Species: $species")
                Text("Gender: $gender")
            }
            // Changing the behaviour depending on what the card should do
            when (mode) {

                CharacterCardMode.SAVE -> {
                    ActionButton("Save", onPrimaryAction)
                }
                CharacterCardMode.REMOVE -> {
                    ActionButton(
                        text = "Remove",
                        onClick = onPrimaryAction,
                        isDestructive = true
                    )
                }

                CharacterCardMode.EDIT -> {
                    Column(
                        modifier = Modifier.fillMaxHeight()
                    ) {

                        ActionButton(
                            text = "Update",
                            onClick = onPrimaryAction,
                            modifier = Modifier.weight(1f)
                        )

                        onSecondaryAction?.let {
                            ActionButton(
                                text = "Delete",
                                onClick = it,
                                modifier = Modifier.weight(1f),
                                isDestructive = true
                            )
                        }
                    }
                }

                CharacterCardMode.PREVIEW -> {
                }
            }
        }
    }
}

 // Reusable button component used inside CharacterCard.
@Composable
private fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDestructive: Boolean = false
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (isDestructive)
                MaterialTheme.colorScheme.error
            else
                MaterialTheme.colorScheme.secondary,

            contentColor =
            if (isDestructive)
                MaterialTheme.colorScheme.onError
            else
                MaterialTheme.colorScheme.onSecondary
        ),
        modifier = modifier
            .width(95.dp)
            .fillMaxHeight(),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text)
    }
}