package com.example.healthbro.presentation.Models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel


data class Wallet(    //struct frm c
    val name: String,
    val amount: Int
)

class SetupViewModel : ViewModel() {
    private val _wallet = mutableStateOf(Wallet(name = "Wallet", amount = 0))
    val wallet: State<Wallet> = _wallet

    val wallet_amt: State<Int> get() = mutableStateOf(_wallet.value.amount)



    fun changeValue(change : Int){
        val newAmount = (_wallet.value.amount + change).coerceAtLeast(0)
        _wallet.value = _wallet.value.copy(amount = newAmount)

    }

    fun saveWallet(name: String){
        _wallet.value = _wallet.value.copy(name = name)

    }


}