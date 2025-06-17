package com.example.healthbro.presentation.Models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider




class TransactionViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewMode::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewMode(context.applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}