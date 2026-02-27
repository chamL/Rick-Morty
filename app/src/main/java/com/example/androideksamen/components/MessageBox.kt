package com.example.androideksamen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class MessageType {
    SUCCESS,
    ERROR,
    INFO
}

@Composable
fun MessageBox(
    message: String,
    type: MessageType = MessageType.INFO
) {

    val containerColor = when (type) {
        MessageType.SUCCESS ->
            MaterialTheme.colorScheme.secondaryContainer

        MessageType.ERROR ->
            MaterialTheme.colorScheme.errorContainer

        MessageType.INFO ->
            MaterialTheme.colorScheme.surfaceContainerHigh
    }

    val contentColor = when (type) {
        MessageType.SUCCESS ->
            MaterialTheme.colorScheme.onSecondaryContainer

        MessageType.ERROR ->
            MaterialTheme.colorScheme.onErrorContainer

        MessageType.INFO ->
            MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                ),
                color = contentColor,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}