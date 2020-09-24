package com.albertcid.transactionsapp.data.network

import com.albertcid.transactionsapp.domain.model.Transaction

interface NetworkDataSource {
    suspend fun getTransactionsList(): Result<List<Transaction>>
}