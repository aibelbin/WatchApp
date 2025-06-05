package com.example.healthbro.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun FinanceTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    Text("Test")
    MaterialTheme(
        content = content
    )
}

@Composable
fun Text(s: String) {

}
