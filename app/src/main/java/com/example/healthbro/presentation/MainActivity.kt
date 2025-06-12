package com.example.healthbro.presentation


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Scaffold
import com.example.healthbro.presentation.screens.HomeScreen
import com.example.healthbro.presentation.screens.MainScreen
import com.example.healthbro.presentation.screens.SetupScreen
import com.example.healthbro.presentation.theme.FinanceTrackerTheme



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(0)  // Disable ALL system window animations
        //window.setBackgroundDrawable(ColorDrawable(Color.Transparent))
        setContent {
            FinanceTrackerTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "MainScreen",
                    modifier = Modifier.fillMaxSize()
                ){

                    composable(
                        route = "MainScreen",
                        exitTransition = {
                            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                        },
                        enterTransition = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                        }
                    ) {
                        MainScreen(navController)
                    }


                    composable(
                        route = "SetupScreen",
                        enterTransition = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                        },
                        exitTransition = {
                            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)  
                        }
                    ) {
                        SetupScreen(navController)
                    }

                    composable(
                        route = "HomeScreen",
                        exitTransition = {
                            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                        },
                        enterTransition = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                        }
                    ) {
                        HomeScreen(navController)
                    }
                }
                }
            }
        }

    }

