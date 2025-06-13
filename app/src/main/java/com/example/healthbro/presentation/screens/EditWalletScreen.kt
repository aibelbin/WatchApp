package com.example.healthbro.presentation.screens

import android.widget.Space
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi

import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.isPopupLayout
import androidx.wear.compose.material.Scaffold
import com.example.healthbro.presentation.Models.SetupViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun EditWalletScreen(navController: NavController) {
    val viewModel: SetupViewModel = viewModel()

    val focusRequester = remember { FocusRequester() }
    val walletName = remember { "Wallet" }
    val interactionSource = remember { MutableInteractionSource() }
    val haptic = LocalHapticFeedback.current

    val animatedAmount by animateIntAsState(
        targetValue = viewModel.wallet_amt.value,
        animationSpec = tween(durationMillis = 100)
    )

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)

                .onRotaryScrollEvent { scrollEvent ->
                    println("Rotary detected: ${scrollEvent.verticalScrollPixels}")
                    viewModel.changeValue((scrollEvent.verticalScrollPixels / 20).toInt())
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    true
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier
                .size(1.dp)
                .alpha(0f)
                .focusRequester(focusRequester)
                .focusable())

            Text(
                text = "Set your amount",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .focusable()
                    .onFocusChanged {
                        println("Focus changed: isFocused=${it.isFocused}")
                    }

            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "₹$animatedAmount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.scale(1f + (animatedAmount % 100) * 0.001f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (animatedAmount != 0) {
                Button(
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(35.dp)
                        .width(120.dp),
                    onClick = {
                        viewModel.saveWallet(walletName)
                        navController.navigate("HomeScreen")
                    }
                ) {
                    Text("Save Wallet")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(300)
        println("Requesting focus now")
        focusRequester.requestFocus()
    }


}