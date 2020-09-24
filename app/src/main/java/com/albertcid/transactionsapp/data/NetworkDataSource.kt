package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.domain.model.Transaction

interface NetworkDataSource {
    suspend fun getTransactionsList(): Result<List<Transaction>>
}