package com.example.healthbro.presentation.Models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class SetupViewModel(context:Context) : ViewModel() {

    private val appContext = context.applicationContext

    private val _walletAmount = mutableStateOf(0)
    val walletAmount: Int get() = _walletAmount.value

    init {
        // Load saved value from DataStore
        viewModelScope.launch {
            DataStore.getWalletAmount(appContext).collect { savedAmt ->
                _walletAmount.value = savedAmt
            }
        }
    }



    fun changeValue(change: Int) {
        _walletAmount.value = (_walletAmount.value + change).coerceAtLeast(0)
        viewModelScope.launch {
            DataStore.saveWalletAmount(appContext, _walletAmount.value)
        }
    }

    fun saveAmount(amount: Int) {
        _walletAmount.value = amount
    }
}