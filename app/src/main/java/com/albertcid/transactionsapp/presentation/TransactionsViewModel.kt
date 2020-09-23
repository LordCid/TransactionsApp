package com.albertcid.transactionsapp.presentation

import androidx.lifecycle.LiveData

interface TransactionsViewModel {
    val viewState: LiveData<TransactionsViewState>
    fun getTransactions()
}