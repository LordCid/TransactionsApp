package com.albertcid.transactionsapp.di

import com.albertcid.transactionsapp.domain.DateChecker
import com.albertcid.transactionsapp.domain.DateCheckerImpl
import com.albertcid.transactionsapp.domain.usecase.GetTransactionsUseCase
import com.albertcid.transactionsapp.domain.usecase.GetTransactionsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindGetTransactionsUseCase(usecase: GetTransactionsUseCaseImpl): GetTransactionsUseCase

    @Binds
    fun bindDateValidator(dateChecker: DateCheckerImpl): DateChecker

}