@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androideksamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.androideksamen.navigation.AppNavigation
import com.example.androideksamen.ui.theme.AndroidEksamenTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidEksamenTheme {
                AppNavigation()
            }
        }
    }
}