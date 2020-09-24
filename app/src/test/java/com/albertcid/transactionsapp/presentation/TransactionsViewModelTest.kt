package com.albertcid.transactionsapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.albertcid.transactionsapp.concreteOtherTransaction
import com.albertcid.transactionsapp.concreteTransaction
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.domain.usecase.GetTransactionsUseCase
import com.albertcid.transactionsapp.presentation.transaction.TransactionsViewState
import com.albertcid.transactionsapp.presentation.transaction.viewmodel.TransactionsViewModel
import com.albertcid.transactionsapp.presentation.transaction.viewmodel.TransactionsViewModelImpl
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class TransactionsViewModelTest {

    private lateinit var sut: TransactionsViewModel

    private val observer = mock<Observer<TransactionsViewState>>()
    private val getTransactionsUseCase = mock<GetTransactionsUseCase>()
    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val captorScreenState = argumentCaptor<TransactionsViewState>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut =
            TransactionsViewModelImpl(
                getTransactionsUseCase,
                dispatcher
            )
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Should show Loading screen when invoke use case`() {
        runBlocking {
            val expected = TransactionsViewState.Loading

            sut.viewState.observeForever(observer)
            sut.getTransactions()

            verify(getTransactionsUseCase).invoke()
            verify(observer).onChanged(expected)

        }
    }


    @Test
    fun `Given success when get transaction list, results are shown into UI`() {
        runBlocking {
            val expectedList = listOf(concreteTransaction, concreteTransaction)
            givenSuccessResultWithValues(expectedList)

            sut.viewState.observeForever(observer)
            sut.getTransactions()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as TransactionsViewState.ShowData
            assertEquals(expectedList, capturedState.transctions)
        }
    }

    @Test
    fun `Given success when get OTHER transaction list, results are shown into UI`() {
        runBlocking {
            val expectedList =  listOf(concreteOtherTransaction, concreteOtherTransaction)
            givenSuccessResultWithValues(expectedList)

            sut.viewState.observeForever(observer)
            sut.getTransactions()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as TransactionsViewState.ShowData
            assertEquals(expectedList, capturedState.transctions)
        }
    }

    @Test
    fun `Given failure when get trasaction list, error is shown in the UI`() {
        runBlocking {
            givenFailureResult()

            sut.viewState.observeForever(observer)
            sut.getTransactions()

            verify(observer, times(2)).onChanged(captorScreenState.capture())
            assert(captorScreenState.secondValue is TransactionsViewState.Error)
        }
    }

    private suspend fun givenSuccessResultWithValues(list: List<Transaction>) {
        given(getTransactionsUseCase.invoke()).willReturn(Result.success(list))
    }

    private suspend fun givenFailureResult() {
        given(getTransactionsUseCase.invoke()).willReturn(Result.failure(mock<Exception>()))
    }

}