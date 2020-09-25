package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.data.Repository
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.domain.toTransactionUIModel
import javax.inject.Inject

class GetTransactionsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetTransactionsUseCase {
    override suspend fun invoke() = repository.getTransactions()
}