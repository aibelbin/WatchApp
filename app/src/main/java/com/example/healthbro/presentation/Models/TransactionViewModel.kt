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

    init {
        // Load existing transactions and wallet balance
        viewModelScope.launch {
            DataStore.getTransactions(appContext).collect { loadedTransactions ->
                _transactions.value = loadedTransactions
            }
        }
        
        viewModelScope.launch {
            DataStore.getWalletAmount(appContext).collect { balance ->
                _walletBalance.value = balance.toDouble()
            }
        }
    }


    fun selectTransactionType(type: TransactionType){
        _selectedType.value = type
    }

    fun addTransaction(selectedType: TransactionType?, amount: Double) {
        val newTransaction = Transaction(type = selectedType, amount = amount, date = Date())
        _transactions.value += newTransaction
        
        // Update wallet balance (subtract the transaction amount)
        _walletBalance.value -= amount

        viewModelScope.launch {
            DataStore.saveTransactions(appContext, _transactions.value)
            DataStore.saveWalletAmount(appContext, _walletBalance.value.toInt())
        }
    }



}






