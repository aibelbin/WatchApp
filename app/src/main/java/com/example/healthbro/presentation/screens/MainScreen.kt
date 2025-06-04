package com.example.healthbro.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import com.example.healthbro.presentation.viewModels.FinanceViewModel

@Composable
fun MainScreen(viewModel: FinanceViewModel = viewModel()){
    Text("Main Screen Ready")
}