package com.example.healthbro.presentation.Models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import java.util.Date
import java.util.UUID


enum class TransactionType{
    PETROL, FOOD, MISS
}

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val type: TransactionType?,
    val amount: Double,
    val date: Date = Date(),
)


class TransactionViewMode(context: Context) : ViewModel() {

    private val appContext = context.applicationContext
    val _transactions = mutableStateOf<List<Transaction>>(emptyList())
    val transactions: List<Transaction> get() = _transactions.value

    private val _selectedType = mutableStateOf<TransactionType?>(null)
    val selectedType: TransactionType? get() = _selectedType.value

    private val _walletBalance = mutableStateOf(0.0)
    val walletBalance: Double get() = _walletBalance.value


    fun selectTransactionType(type: TransactionType){
        val selectedTransactions = _transactions.value.filter { it.type == type }
        _transactions.value = selectedTransactions
    }

    fun addTransaction(selectedType: TransactionType?, amount: Double) {
        val newTransaction = Transaction(type = selectedType, amount = amount, date = Date())
        _transactions.value += newTransaction
        _walletBalance.value -= amount

        viewModelScope.launch {
            DataStore.saveTransactions(appContext, _transactions.value)
            DataStore.saveWalletAmount(appContext, _walletBalance.value.toInt())
        }
    }



}






