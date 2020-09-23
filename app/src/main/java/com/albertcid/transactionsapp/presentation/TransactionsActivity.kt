package com.albertcid.transactionsapp.presentation

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.albertcid.transactionsapp.R

class TransactionsActivity : AppCompatActivity() {

    lateinit var viewModelFactory: ViewModelProvider.NewInstanceFactory
    private lateinit var viewModel: TransactionsViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[TransactionsViewModelImpl::class.java]
        viewModel.viewState.observe(::getLifecycle, ::updateUI)
        viewModel.getTransactions()
    }

    private fun updateUI(screenState: TransactionsViewState) {
        when (screenState) {
            is TransactionsViewState.Loading -> {}
            is TransactionsViewState.ShowData -> {

            }
            is TransactionsViewState.Error -> {

            }
        }
    }


}