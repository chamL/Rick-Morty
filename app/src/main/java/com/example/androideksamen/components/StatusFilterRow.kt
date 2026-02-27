package com.example.androideksamen.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androideksamen.viewmodel.CharacterViewModel
import com.example.androideksamen.viewmodel.StatusFilter

@Composable
fun StatusFilterRow(viewModel: CharacterViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        StatusFilter.values().forEach { filter ->

            FilterChip(
                selected = viewModel.selectedFilter == filter,
                onClick = { viewModel.setFilter(filter) },
                label = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            filter.name
                                .lowercase()
                                .replaceFirstChar { it.uppercase() }
                        )
                    }
                },
                modifier = Modifier.weight(1f),
                colors = FilterChipDefaults.filterChipColors(

                    selectedContainerColor =
                    MaterialTheme.colorScheme.primary,
                    selectedLabelColor =
                    MaterialTheme.colorScheme.onPrimary,

                    containerColor =
                    MaterialTheme.colorScheme.surfaceContainerLowest,
                    labelColor =
                    MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}