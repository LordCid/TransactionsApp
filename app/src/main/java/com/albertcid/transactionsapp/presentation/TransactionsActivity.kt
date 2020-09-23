package com.albertcid.transactionsapp.presentation

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albertcid.transactionsapp.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TransactionsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.NewInstanceFactory
    private lateinit var viewModel: TransactionsViewModel
    private lateinit var progressDialog: ProgressDialog

    private lateinit var transactionsAdapter: TransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transactionsAdapter = TransactionsAdapter(DateFormat.getDateFormat(this))
        setUpUI()
        setViewModel()
    }

    private fun setUpUI() {
        progressDialog = ProgressDialog(this)
        listView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = transactionsAdapter
        }

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
            is TransactionsViewState.Loading -> showLoadingDialogFragment()
            is TransactionsViewState.ShowData -> {

            }
            is TransactionsViewState.Error -> showErrorDialogFragment()
        }
    }
    private fun showLoadingDialogFragment() {
        progressDialog.setMessage(getString(R.string.downloading_title_dialog))
        progressDialog.show()
    }

    private fun showErrorDialogFragment() {
        progressDialog.dismiss()
        val errorDialog = ErrorDialogFragment()
        errorDialog.show(supportFragmentManager, "error")
    }

}