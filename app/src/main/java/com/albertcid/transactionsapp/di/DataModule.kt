package com.albertcid.transactionsapp.di

import com.albertcid.transactionsapp.data.Repository
import com.albertcid.transactionsapp.data.RepositoryImpl
import com.albertcid.transactionsapp.data.network.NetworkDataSource
import com.albertcid.transactionsapp.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    fun bindNetworkDataSource(dataSource: NetworkDataSourceImpl): NetworkDataSource
}