package com.example.healthbro.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween

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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue


import androidx.wear.compose.material.*
import androidx.compose.runtime.remember as remember


@SuppressLint("RememberReturnType")
@Composable
fun SetupScreen(navController: NavController) {
    val viewModel: SetupViewModel = viewModel()
    val focusRequester = remember {FocusRequester()}

    var walletName by remember { mutableStateOf("") }

    val animatedAmount by animateIntAsState(
        targetValue = viewModel.wallet_amt.value,
        animationSpec = tween(durationMillis = 100)
    )

    // val haptic = remember { HapticFeedback.getInstance() }       not working


    RotaryHandler(onRotaryScrollEvent = { scrollEvent ->

        viewModel.changeValue((scrollEvent.verticalScrollPixels / 10).toInt())
       //  haptic.perform(HapticFeedbackType.TEXT_INPUT_FOCUS)
    })

    Scaffold(
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .focusable(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "₹${viewModel.wallet_amt.value}",
                style = MaterialTheme.typography.displayLarge
            )

            Button(onClick = { viewModel.updateAmount(100) }) {
                Text("+100")
            }

            Button(onClick = { viewModel.updateAmount(-100) }) {
                Text("-100")
            }

            TextField(
                value = walletName,
                onValueChange =  {walletName = it},
                label = { Text("Wallet Name")}
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                viewModel.SaveWallet(walletName)
                navController.navigate("main")
            },
                enabled = walletName.isNotEmpty(
                )) {
                    Text("Create Wallet")

                }
            Text(
                text = "₹$animatedAmount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.scale(1f + (viewModel.wallet_amt.value % 100) * 0.001f)
            )

        }
    }
}

private operator fun Any.getValue(nothing: Nothing?, property: KProperty<*>): Any {

}


@Composable
fun RotaryHandler(onRotaryScrollEvent: (Any?) -> Unit) {

}





