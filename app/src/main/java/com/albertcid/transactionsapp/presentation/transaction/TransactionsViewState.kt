package com.albertcid.transactionsapp.presentation.transaction

import com.albertcid.transactionsapp.domain.model.Transaction

sealed class TransactionsViewState {
    object Loading: TransactionsViewState()
    class ShowData(val transctions: List<Transaction>): TransactionsViewState()
    object Error: TransactionsViewState()
}