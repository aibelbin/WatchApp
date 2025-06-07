package com.example.healthbro.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.MaterialTheme.colors

val LightColors = androidx.wear.compose.material.Colors(
    primary = Color(0xFF1EB980),
    secondary = Color(0xFF045D56),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF2F2F2),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

val themeColor = androidx.wear.compose.material.Colors(
    primary = Color(0xFFFFD600),  // Vibrant yellow for primary actions
    secondary = Color(0xFF424242), // Dark gray for secondary elements
    background = Color.Black, // Pure black background
    surface = Color.Black,   // Slightly elevated surfaces
    onPrimary = Color.Black,       // Black text/icons on yellow
    onSecondary = Color(0xFFFFD600), // Yellow text/icons on gray
    onBackground = Color(0xFFFFD600), // Yellow text on black
    onSurface = Color(0xFFFFD600)
)


@Composable
fun FinanceTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = themeColor

    Text("Test")
    MaterialTheme(
        colors = colors,
        content = content
    )
}

@Composable
fun Text(s: String) {

}
