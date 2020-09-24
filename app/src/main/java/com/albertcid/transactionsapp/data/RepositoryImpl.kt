package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.domain.model.Transaction
import org.joda.time.DateTime

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : Repository {
    override suspend fun getTransactions() = networkDataSource.getTransactionsList()
}