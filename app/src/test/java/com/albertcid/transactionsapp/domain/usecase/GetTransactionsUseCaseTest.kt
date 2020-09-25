package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.*
import com.albertcid.transactionsapp.data.Repository
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.presentation.model.TransactionUIModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime
import org.junit.Before

import org.junit.Test
import java.lang.Exception

class GetTransactionsUseCaseTest {

    private lateinit var sut: GetTransactionsUseCase
    private val repository = mock<Repository>()

    @Before
    fun setUp() {
        sut = GetTransactionsUseCaseImpl(repository)
    }

    @Test
    fun `Given concrete transaction results, should return sorted transaction list by recent to latter date `() {
        runBlocking {
            val result = listOf(
                secondTransaction,
                fourthTransaction,
                firstTransaction,
                thirdTransaction
            )
            given(repository.getTransactions()).willReturn(Result.success(result))
            val expected = listOf(
                firstTransactionUI,
                secondTransactionUI,
                thirdTransactionUI,
                fourthTransactionUI
            )

            val actual = sut.invoke()

            verify(repository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given OTHER concrete transaction results, should return sorted transaction list by recent to latter date `() {
        runBlocking {
            val result = listOf(
                fourthOtherTransaction,
                secondOtherTransaction,
                thirdOtherTransaction,
                firstOtherTransaction
            )
            given(repository.getTransactions()).willReturn(Result.success(result))
            val expected = listOf(
                firstOtherTransactionUI,
                secondOtherTransactionUI,
                thirdOtherTransactionUI,
                fourthOtherTransactionUI
            )

            val actual = sut.invoke()

            verify(repository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given failure result, should return it`() {
        runBlocking {
            val expected = Result.failure<List<Transaction>>( mock<Exception>())
            given(repository.getTransactions()).willReturn(expected)

            val actual = sut.invoke()

            verify(repository).getTransactions()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Given transaction results with fee, should return transaction model amout plus fee`() {
        runBlocking {

            val result = listOf(firstTransaction)
            given(repository.getTransactions()).willReturn(Result.success(result))
            val expected = listOf(firstTransactionUI)

            val actual = sut.invoke()

            verify(repository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }
    }
    
}