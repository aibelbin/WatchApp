package com.example.healthbro.presentation


import DataStore
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Scaffold
import com.example.healthbro.presentation.Models.SetupViewModel
import com.example.healthbro.presentation.screens.HomeScreen
import com.example.healthbro.presentation.screens.MainScreen
import com.example.healthbro.presentation.screens.SetupScreen
import com.example.healthbro.presentation.theme.FinanceTrackerTheme
import com.example.healthbro.presentation.Models.SetupViewModelFactory
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.coroutineContext


class MainActivity : ComponentActivity() {

    val viewModel: SetupViewModel by viewModels()
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(0)

        setContent {
            FinanceTrackerTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val viewModel: SetupViewModel = viewModel(
                    factory = SetupViewModelFactory(context)
                )
                val isFirstRun by DataStore.isFirstRun(context).collectAsState(initial = true)

                LaunchedEffect(isFirstRun) {
                    if (!isFirstRun) {
                        navController.navigate("HomeScreen") {
                            popUpTo("MainScreen") { inclusive = true }
                        }
                    }
                }


                NavHost(
                    navController = navController,
                    startDestination = if (isFirstRun) "MainScreen" else "HomeScreen",
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
                        SetupScreen(
                            onSetupComplete = {
                                navController.navigate("HomeScreen") {
                                    popUpTo("MainScreen") { inclusive = true }
                                }
                            },
                            viewModel = viewModel,
                            navController = navController
                        )
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
                        HomeScreen(navController, viewModel = viewModel)
                    }
                }
                }
            }
        }

    }

