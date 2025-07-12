package com.example.healthbro.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.example.healthbro.presentation.Models.SetupViewModel




@Composable
fun HomeScreen(navController: NavController, viewModel: SetupViewModel) {

//    val viewModel: SetupViewModel = viewModel()
    val display_Wal_Amt = viewModel.walletAmount


    Scaffold(
        //nothing for now
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "₹$display_Wal_Amt",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.scale(1f + (viewModel.walletAmount % 100) * 0.001f)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(30.dp)
                    .width(135.dp),
                onClick =
                    {
                        navController.navigate("SetupScreen") {
                            //#
                        }
                    }) {
                Text(
                    "Edit Amount"
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
                Button(
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(30.dp)
                        .width(135.dp),
                    onClick =
                        {
                            navController.navigate("setTransactionType") {

                            }
                        }) {
                    Text(
                        "Add Transaction"
                    )
                }
            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(30.dp)
                    .width(135.dp),
                onClick =
                    {
                        navController.navigate("TransactionHistory")
                    }) {
                Text(
                    "View History"
                )
            }

            }
        }
    }
