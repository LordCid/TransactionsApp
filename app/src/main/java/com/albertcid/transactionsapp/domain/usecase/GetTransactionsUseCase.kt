package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.presentation.model.TransactionUIModel

interface GetTransactionsUseCase {
    suspend operator fun invoke(): Result<List<TransactionUIModel>>
}