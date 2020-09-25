package com.albertcid.transactionsapp.presentation.transaction

import com.albertcid.transactionsapp.presentation.transaction.model.TransactionUIModel

sealed class TransactionsViewState {
    object Loading: TransactionsViewState()
    class ShowData(val transactions: List<TransactionUIModel>): TransactionsViewState()
    object Error: TransactionsViewState()
}