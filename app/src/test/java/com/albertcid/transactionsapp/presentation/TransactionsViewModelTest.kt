package com.albertcid.transactionsapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.albertcid.transactionsapp.*
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
import org.joda.time.DateTime
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
            //GIVEN
            val expected = TransactionsViewState.Loading

            //WHEN
            sut.viewState.observeForever(observer)
            sut.getTransactions()

            //THEN
            verify(getTransactionsUseCase).invoke()
            verify(observer).onChanged(expected)
        }
    }


    @Test
    fun `Given success when get transaction list, results are shown into UI sorted by date`() {
        runBlocking {
            //GIVEN
            val result = listOf(
                secondTransaction,
                fourthTransaction,
                firstTransaction,
                thirdTransaction
            )
            given(getTransactionsUseCase.invoke()).willReturn(Result.success(result))
            val expected = listOf(
                fourthTransactionUI,
                thirdTransactionUI,
                secondTransactionUI,
                firstTransactionUI
            )


            //WHEN
            sut.viewState.observeForever(observer)
            sut.getTransactions()

            //THEN
            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as TransactionsViewState.ShowData
            assertEquals(expected, capturedState.transactions)
        }
    }

    @Test
    fun `Given success when get OTHER transaction list, results are shown into UI`() {
        runBlocking {
            //GIVEN
            val result = listOf(
                fourthOtherTransaction,
                secondOtherTransaction,
                thirdOtherTransaction,
                firstOtherTransaction
            )
            given(getTransactionsUseCase.invoke()).willReturn(Result.success(result))
            val expected = listOf(
                fourthOtherTransactionUI,
                thirdOtherTransactionUI,
                secondOtherTransactionUI,
                firstOtherTransactionUI
            )

            //WHEN
            sut.viewState.observeForever(observer)
            sut.getTransactions()

            //THEN
            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as TransactionsViewState.ShowData
            assertEquals(expected, capturedState.transactions)
        }
    }

    @Test
    fun `Given failure when get transaction list, error is shown in the UI`() {
        runBlocking {
            //GIVEN
            given(getTransactionsUseCase.invoke()).willReturn(Result.failure(mock<Exception>()))

            //WHEN
            sut.viewState.observeForever(observer)
            sut.getTransactions()

            //THEN
            verify(observer, times(2)).onChanged(captorScreenState.capture())
            assert(captorScreenState.secondValue is TransactionsViewState.Error)
        }
    }

    @Test
    fun `Given transaction results with fee, should show transaction model amount plus fee in UI`() {
        runBlocking {
            //GIVEN
            val result = listOf(firstTransaction)
            given(getTransactionsUseCase.invoke()).willReturn(Result.success(result))
            val expected = listOf(firstTransactionUI)

            //WHEN
            sut.viewState.observeForever(observer)
            sut.getTransactions()

            //THEN
            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as TransactionsViewState.ShowData
            assertEquals(expected, capturedState.transactions)
        }
    }

    @Test
    fun `Given transaction results with same Id, should show most recent in UI`() {
        runBlocking {
            //GIVEN
            val firstTransaction = getTransactionByDate("2018-07-11T22:49:24.000Z")
            val secondTransaction = getTransactionByDate("2018-07-24T18:10:10.000Z")
            val result = listOf(firstTransaction, secondTransaction)
            given(getTransactionsUseCase.invoke()).willReturn(Result.success(result))
            val expected = listOf(getUIModelTransactionByDate("2018-07-24T18:10:10.000Z"))

            //WHEN
            sut.viewState.observeForever(observer)
            sut.getTransactions()

            //THEN
            verify(observer, times(2)).onChanged(captorScreenState.capture())
            val capturedState = captorScreenState.secondValue as TransactionsViewState.ShowData
            assertEquals(expected, capturedState.transactions)
        }
    }

}