package com.example.healthbro.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Scaffold
import com.example.healthbro.presentation.screens.MainScreen
import com.example.healthbro.presentation.theme.FinanceTrackerTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTrackerTheme {
                Scaffold (modifier = Modifier.fillMaxHeight()) {
                    MainScreen()
                    }
                }
            }
        }

    }

