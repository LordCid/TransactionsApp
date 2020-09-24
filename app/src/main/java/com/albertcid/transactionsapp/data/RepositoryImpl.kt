package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.data.network.NetworkDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : Repository {
    override suspend fun getTransactions() = networkDataSource.getTransactionsList()
}