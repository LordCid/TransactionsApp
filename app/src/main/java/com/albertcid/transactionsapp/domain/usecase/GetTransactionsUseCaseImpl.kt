package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.data.Repository
import com.albertcid.transactionsapp.domain.toTransactionUIModel
import com.albertcid.transactionsapp.presentation.model.TransactionUIModel
import javax.inject.Inject

class GetTransactionsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetTransactionsUseCase {
    override suspend fun invoke(): Result<List<TransactionUIModel>> {
        val result = repository.getTransactions()
        return runCatching {
            result.getOrThrow().sortedBy { it.date }.map { it.toTransactionUIModel() }
        }
    }
}