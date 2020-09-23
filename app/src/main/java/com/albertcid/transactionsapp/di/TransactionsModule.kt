package com.albertcid.transactionsapp.di

import androidx.lifecycle.ViewModelProvider
import com.albertcid.transactionsapp.presentation.TransactionViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface TransactionsModule {
    @Binds
    fun bindTransactionViewModelFactory(viewModelFactory: TransactionViewModelFactory): ViewModelProvider.NewInstanceFactory
}