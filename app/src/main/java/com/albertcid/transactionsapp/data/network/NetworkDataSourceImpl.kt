package com.albertcid.transactionsapp.data.network

import com.albertcid.transactionsapp.data.network.model.toTransactionModel
import com.albertcid.transactionsapp.domain.model.Transaction
import retrofit2.awaitResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkDataSource {
    override suspend fun getTransactionsList(): Result<List<Transaction>> {
        return runCatching { apiService.getTransactions().awaitResponse() }.fold(
            onSuccess = {
                val resultList = it.body()?.let { response ->
                    response.map { netModel -> netModel.toTransactionModel() }
                }.orEmpty()
                Result.success(resultList)
            },
            onFailure = { Result.failure(it) }
        )
    }
}