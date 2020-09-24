package com.albertcid.transactionsapp.di


import com.albertcid.transactionsapp.presentation.TransactionsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        DomainModule::class
    ]
)
interface ActivityBuilder {
    @ContributesAndroidInjector(modules = [TransactionsModule::class])
    fun bindMainListActivity(): TransactionsActivity

}