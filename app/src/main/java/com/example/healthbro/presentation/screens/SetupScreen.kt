package com.example.healthbro.presentation.screens

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.TimeText
import androidx.compose.foundation.layout.*
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.core.view.InputDeviceCompat
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi

import androidx.compose.ui.input.rotary.RotaryScrollEvent
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun SetupScreen(navController: NavController) {

    val viewModel: SetupViewModel = viewModel()

    val focusRequester = remember { FocusRequester() }
    var walletName by remember { mutableStateOf("") }
    val haptic = LocalHapticFeedback.current

    val animatedAmount by animateIntAsState(
        targetValue = viewModel.wallet_amt.value,
        animationSpec = tween(durationMillis = 100)
    )

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .focusable()
                .onRotaryScrollEvent { scrollEvent ->
                    viewModel.changeValue((scrollEvent.verticalScrollPixels / 10).toInt())
                   // haptic.perform(HapticFeedbackType.TextInputFocus)
                    true
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "₹${viewModel.wallet_amt.value}",
                style = MaterialTheme.typography.displayLarge
            )

            Button(onClick = { viewModel.changeValue(100) }) {
                Text("+100")
            }

            Button(onClick = { viewModel.changeValue(-100) }) {
                Text("-100")
            }

            TextField(
                value = walletName,
                onValueChange = { walletName = it },
                label = { Text("Wallet Name") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.saveWallet(walletName)
                    navController.navigate("main")
                },
                enabled = walletName.isNotEmpty()
            ) {
                Text("Create Wallet")
            }

            Text(
                text = "₹$animatedAmount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.scale(1f + (viewModel.wallet_amt.value % 100) * 0.001f)
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}