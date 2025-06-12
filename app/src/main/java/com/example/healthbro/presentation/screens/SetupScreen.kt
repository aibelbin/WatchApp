package com.example.healthbro.presentation.screens

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.wear.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi

import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.isPopupLayout
import androidx.wear.compose.material.Scaffold
import com.example.healthbro.presentation.Models.SetupViewModel

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun SetupScreen(navController: NavController) {

    val viewModel: SetupViewModel = viewModel()

    val focusRequester = remember { FocusRequester() }
    val walletName = remember { "Wallet" }
    val haptic = LocalHapticFeedback.current
    //var lastHapticTime by remember { mutableStateOf(0L) }

    val animatedAmount by animateIntAsState(
        targetValue = viewModel.wallet_amt.value,
        animationSpec = tween(durationMillis = 100)
    )

    Scaffold(
        //timeText = { TimeText() },
        //vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .focusRequester(focusRequester)
                .focusable()
                .onRotaryScrollEvent { scrollEvent ->
                    val now = System.currentTimeMillis()
                    viewModel.changeValue((scrollEvent.verticalScrollPixels / 10).toInt())
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    true
                },

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Set your amount",
                style = MaterialTheme.typography.titleLarge

            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "₹$animatedAmount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.scale(1f + (viewModel.wallet_amt.value % 100) * 0.001f)
            )
            Spacer(modifier = Modifier.height(20.dp))

            if (viewModel.wallet_amt.value != 0){
                Button(
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(20.dp)
                        .width(100.dp),
                    onClick = {
                        viewModel.saveWallet(walletName)
                        navController.navigate("HomeScreen")
                    }
                ) {
                    Text("Create Wallet")
                }
            }


        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}