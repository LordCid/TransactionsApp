package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.data.network.NetworkDataSource

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : Repository {
    override suspend fun getTransactions() = networkDataSource.getTransactionsList()
}