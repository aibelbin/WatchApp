package com.example.healthbro.presentation.screens


import android.content.Context
import com.example.healthbro.presentation.Models.TransactionType
import com.example.healthbro.presentation.Models.TransactionViewMode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material3.MaterialTheme
import com.example.healthbro.presentation.Models.TransactionViewModelFactory


@Composable
fun setTransactionType(navController: NavController, context : Context) {


    val appContext = context.applicationContext
    val viewModel: TransactionViewMode = viewModel(
        factory = TransactionViewModelFactory(context)
    )

    Scaffold(
       // timeText = { TimeText() }, time bar is taking up too much spcae
        //vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(30.dp)
                    .width(130.dp),
                onClick =
                    {
                        viewModel.selectTransactionType(TransactionType.PETROL)
                        navController.navigate("SetTransactionAmount")
                    }) {
                androidx.wear.compose.material.Text(
                    "Petrol"
                )
            }

            Spacer(modifier = Modifier
                .size(8.dp))

                Button(
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(30.dp)
                        .width(130.dp),
                    onClick =
                        {
                            viewModel.selectTransactionType(TransactionType.FOOD)
                            navController.navigate("SetTransactionAmount")
                        }) {
                    androidx.wear.compose.material.Text(
                        "Food"
                    )
                }

            Spacer(modifier = Modifier
                .size(8.dp))

                    Button(
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(30.dp)
                            .width(130.dp),
                        onClick =
                            {
                                viewModel.selectTransactionType(TransactionType.MISS)
                                navController.navigate("SetTransactionAmount")
                            }) {
                        androidx.wear.compose.material.Text(
                            "Miss"
                        )
                    }


        }
    }
}