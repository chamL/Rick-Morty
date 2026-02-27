package com.example.androideksamen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    // list of the screens shown in the bottom bar
    val screens = listOf(
        Screen.List,
        Screen.Create,
        Screen.Saved
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 6.dp
    ) {

        val backStackEntry =
            navController.currentBackStackEntryAsState()

        //  which route is active to highlight the tab
        val currentRoute =
            backStackEntry.value?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,

                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(Screen.List.route)
                        launchSingleTop = true
                    }
                },

                label = {
                    Text(
                        screen.title,
                        style = MaterialTheme.typography.labelLarge
                    )
                },

                icon = {
                    Icon(
                        imageVector = when (screen) {
                            Screen.List -> Icons.Default.Home
                            Screen.Create -> Icons.Default.Add
                            Screen.Saved -> Icons.Default.List
                        },
                        contentDescription = screen.title
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor =
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 1f),
                    unselectedTextColor =
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 1f)
                )
            )
        }
    }
}