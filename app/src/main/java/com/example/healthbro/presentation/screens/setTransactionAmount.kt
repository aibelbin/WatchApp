package com.example.healthbro.presentation.screens



import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import com.example.healthbro.presentation.Models.TransactionViewMode
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material3.MaterialTheme
import com.example.healthbro.presentation.Models.SetupViewModel
import com.example.healthbro.presentation.Models.SetupViewModelFactory
import com.example.healthbro.presentation.Models.TransactionViewModelFactory
import kotlinx.coroutines.delay


@Composable
fun setTransactionAmount(){

    val focusRequester = remember { FocusRequester() }
    val walletName = remember { "Wallet" }
    val interactionSource = remember { MutableInteractionSource() }
    val haptic = LocalHapticFeedback.current


    //var counter by remember { mutableStateOf(0) }

    val context = LocalContext.current
    val viewModel: SetupViewModel = viewModel(
        factory = SetupViewModelFactory(context)
    )
    val viewModell: TransactionViewMode = viewModel(
        factory = TransactionViewModelFactory(context)
    )

    val animatedAmount by animateIntAsState(
        targetValue = viewModel.walletAmount,
        animationSpec = tween(durationMillis = 100)
    )

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .onRotaryScrollEvent { scrollEvent ->
                    println("Rotary detected: ${scrollEvent.verticalScrollPixels}")
//                    counter += (scrollEvent.verticalScrollPixels / 20).toInt()
                    viewModel.changeValue((scrollEvent.verticalScrollPixels / 20).toInt())
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    true
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "$animatedAmount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.scale(1f + (animatedAmount % 100) * 0.001f)

            )
            Spacer(modifier = Modifier
                .size(10.dp)
                .alpha(0f)
                .focusRequester(focusRequester)
                .focusable())

            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(30.dp)
                    .width(120.dp),
                onClick = {
                         viewModell.addTransaction(viewModell.selectedType, viewModel.walletAmount.toDouble() )

                }
            ) {
                Text("Save")
            }


        }
    }

    LaunchedEffect(Unit) {
        delay(300)
        println("Requesting focus now")
        focusRequester.requestFocus()
    }
}