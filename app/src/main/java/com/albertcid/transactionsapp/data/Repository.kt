package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.domain.model.Transaction

interface Repository {
    suspend fun getTransactions(): Result<List<Transaction>>
}