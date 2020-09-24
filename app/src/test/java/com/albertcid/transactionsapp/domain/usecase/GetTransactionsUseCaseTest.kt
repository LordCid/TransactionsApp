package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.data.Repository
import com.albertcid.transactionsapp.domain.model.Transaction
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
    private val respository = mock<Repository>()

    private val firstTransaction = getTransactionByDate("2018-07-11T22:49:24.000Z")
    private val secondTransaction = getTransactionByDate("2018-07-14T16:54:27.000Z")
    private val thirdTransaction =  getTransactionByDate("2018-07-24T18:10:10.000Z")
    private val fourthTransaction = getTransactionByDate("2018-07-29T17:56:43.000Z")

    private val firstOtherTransaction = getOtherTransactionByDate("2018-07-11T22:49:24.000Z")
    private val secondOtherTransaction = getOtherTransactionByDate("2018-07-14T16:54:27.000Z")
    private val thirdOtherTransaction = getOtherTransactionByDate("2018-07-24T18:10:10.000Z")
    private val fourthOtherTransaction = getOtherTransactionByDate("2018-07-29T17:56:43.000Z")

    @Before
    fun setUp() {
        sut = GetTransactionsUseCaseImpl(respository)
    }

    @Test
    fun `Given concrete transaction results, should return sorted transaction list by recent to latter date `() {
        runBlocking {
            val result = listOf(secondTransaction, fourthTransaction, firstTransaction, thirdTransaction)
            given(respository.getTransactions()).willReturn(Result.success(result))
            val expected = listOf(firstTransaction, secondTransaction, thirdTransaction, fourthTransaction)

            val actual = sut.invoke()

            verify(respository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given OTHER concrete transaction results, should return sorted transaction list by recent to latter date `() {
        runBlocking {
            val result = listOf(fourthOtherTransaction, secondOtherTransaction, thirdOtherTransaction, firstOtherTransaction)
            given(respository.getTransactions()).willReturn(Result.success(result))
            val expected = listOf(firstOtherTransaction, secondOtherTransaction, thirdOtherTransaction, fourthOtherTransaction)

            val actual = sut.invoke()

            verify(respository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given failure result, should return it`() {
        runBlocking {
            val expected = Result.failure<List<Transaction>>( mock<Exception>())
            given(respository.getTransactions()).willReturn(expected)

            val actual = sut.invoke()

            verify(respository).getTransactions()
            assertEquals(expected, actual)
        }
    }

    private fun getTransactionByDate(date: String) = Transaction(
        id = 123,
        date = DateTime(date),
        fee = -35.06,
        amount = -234.56,
        description = "description"
    )

    private fun getOtherTransactionByDate(date: String) = Transaction(
        id = 4567,
        date = DateTime(date),
        amount = -234.56,
        description = "description"
    )


}