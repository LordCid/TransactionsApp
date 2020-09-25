package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.domain.model.Transaction

interface GetTransactionsUseCase {
    suspend operator fun invoke(): Result<List<Transaction>>
}