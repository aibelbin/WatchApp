package com.example.healthbro.presentation.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class SetupViewModel : ViewModel() {
    private val priv_wallet_amt =  mutableStateOf(0)
    val wallet_amt: State<Int> =  priv_wallet_amt

    fun changeValue(change : Int){
        priv_wallet_amt.value = (priv_wallet_amt.value + change).coerceAtLeast(0)
    }

    fun saveWallet(name: String){
        val newWallet = Wallet( name = name, balance = wallet_amt.value)
    }

}