package com.albertcid.transactionsapp.presentation

import androidx.lifecycle.*
import com.albertcid.transactionsapp.domain.usecase.GetTransactionsUseCase
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
                onSuccess = {
                    _viewState.value = TransactionsViewState.ShowData(it)
                },
                onFailure = {
                    _viewState.value = TransactionsViewState.Error

                }
            )
        }
    }
}

class TransactionViewModelFactory @Inject constructor(
    private val getGroupListUseCase: GetTransactionsUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionsViewModelImpl(getGroupListUseCase, ioDispatcher) as T
    }
}