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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material3.MaterialTheme

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        timeText = { TimeText() },
        //vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ){
         Column( modifier = Modifier
                 .fillMaxSize()
                .background(Color.Black),
                 horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Center
    ){
             Text(
                 text = "Welcome to the Finance app",
                 style = MaterialTheme.typography.titleLarge.copy(
                     textAlign = TextAlign.Center
                 ),
                 modifier = Modifier.fillMaxWidth()
             )



             Spacer(modifier = Modifier.height(30.dp))

             Button(
                 shape = RoundedCornerShape(50),
                 modifier = Modifier
                     .height(35.dp)
                     .width(130.dp),
                 onClick =
             {
                 navController.navigate("SetupScreen"){
                     //#
                 }
             }) {
                 Text(
                     "Get Started"
                 )
             }
         }
    }
}