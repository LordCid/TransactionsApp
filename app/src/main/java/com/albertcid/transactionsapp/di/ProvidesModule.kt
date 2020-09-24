package com.albertcid.transactionsapp.di

import com.albertcid.transactionsapp.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module
object ProvidesModule {
    @Provides
    @JvmStatic
    fun provideApiService() = ApiService.create()
}