package com.albertcid.transactionsapp.presentation.transaction.viewmodel

import androidx.lifecycle.*
import com.albertcid.transactionsapp.domain.toTransactionUIModel
import com.albertcid.transactionsapp.domain.usecase.GetTransactionsUseCase
import com.albertcid.transactionsapp.presentation.transaction.TransactionsViewState
import com.albertcid.transactionsapp.presentation.transaction.model.TransactionUIModel
import kotlinx.coroutines.*
import javax.inject.Inject

class TransactionsViewModelImpl(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : TransactionsViewModel, ViewModel(), CoroutineScope by MainScope() {

    private val _viewState = MutableLiveData<TransactionsViewState>()
    override val viewState: LiveData<TransactionsViewState>
        get() = _viewState

    override fun getTransactions() {
        launch {
            _viewState.value = TransactionsViewState.Loading
            val results = withContext(ioDispatcher) { getTransactionsUseCase() }
            results.fold(
                onSuccess = { dataList ->
                    val resList = filterMosTransactionWithSameId(
                        dataList.map{ it.toTransactionUIModel() }.sortedByDescending { it.date }
                    )
                    _viewState.value = TransactionsViewState.ShowData(resList)
                },
                onFailure = {
                    _viewState.value = TransactionsViewState.Error

                }
            )
        }
    }
}

private fun filterMosTransactionWithSameId(list: List<TransactionUIModel>): List<TransactionUIModel> {
    val mutableList = mutableListOf<TransactionUIModel>()
    list.groupBy { it.id }.forEach {
        val transaction = it.value.maxBy { item -> item.date }
        if (transaction != null) {
            mutableList.add(transaction)
        }
    }
    return mutableList
}

class TransactionViewModelFactory @Inject constructor(
    private val getGroupListUseCase: GetTransactionsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionsViewModelImpl(
            getGroupListUseCase,
            ioDispatcher
        ) as T
    }
}