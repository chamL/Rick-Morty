package com.example.androideksamen.navigation

sealed class Screen(
    val route: String,
    val title: String
) {
    object List : Screen("list", "Characters")

    object Create : Screen("create", "Create")

    object Saved : Screen("saved", "Saved")
}