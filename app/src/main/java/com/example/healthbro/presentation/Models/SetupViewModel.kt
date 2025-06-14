package com.example.healthbro.presentation.Models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel

class SetupViewModel : ViewModel() {

    private val _walletAmount = mutableStateOf(0)
    val walletAmount: Int get() = _walletAmount.value


    fun changeValue(change: Int) {
        _walletAmount.value = (_walletAmount.value + change).coerceAtLeast(0)
    }

    fun saveAmount(amount: Int) {
        _walletAmount.value = amount
    }
}