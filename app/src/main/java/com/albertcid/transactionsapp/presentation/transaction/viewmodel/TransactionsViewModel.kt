package com.albertcid.transactionsapp.presentation.transaction.viewmodel

import androidx.lifecycle.LiveData
import com.albertcid.transactionsapp.presentation.transaction.TransactionsViewState

interface TransactionsViewModel {
    val viewState: LiveData<TransactionsViewState>
    fun getTransactions()
}