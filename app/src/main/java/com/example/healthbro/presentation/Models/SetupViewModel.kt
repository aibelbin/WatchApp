package com.example.healthbro.presentation.Models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel


data class Wallet(    //struct frm c
    val name: String,
    val amount: Int
)

class SetupViewModel : ViewModel() {
    private val priv_wallet_amt =  mutableStateOf(0)
    val wallet_amt: State<Int> =  priv_wallet_amt

    fun changeValue(change : Int){
        priv_wallet_amt.value = (priv_wallet_amt.value + change).coerceAtLeast(0)
    }

    fun saveWallet(name: String){
        val newWallet = Wallet( name = name, amount = wallet_amt.value)
    }


}