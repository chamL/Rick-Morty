package com.example.androideksamen.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(

    primary = BurlywoodDark,
    onPrimary = OnBurlywood,

    secondary = LightGreen,
    onSecondary = OnLightGreen,

    background = Burlywood.copy(alpha = 0.25f),
    onBackground = OnBurlywood,

    surface = Burlywood,
    onSurface = OnBurlywood,

    surfaceVariant = Burlywood.copy(alpha = 0.35f),

    error = Tomato,
    onError = OnTomato
)


private val DarkColorScheme = darkColorScheme(
    primary = BurlywoodDark,
    secondary = BurlywoodDark,
    tertiary = Burlywood
)

@Composable
fun AndroidEksamenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}