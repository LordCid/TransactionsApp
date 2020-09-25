package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.concreteOtherTransaction
import com.albertcid.transactionsapp.concreteTransaction
import com.albertcid.transactionsapp.data.network.NetworkDataSource
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RepositoryTest {

    private lateinit var sut: Repository
    private val networkDataSource = mock<NetworkDataSource>()

    @Before
    fun setUp() {
        sut = RepositoryImpl(networkDataSource)
    }

    @Test
    fun `Given results, When get transaction list, data is get from network datasource`() {
        runBlocking {
            //GIVEN
            val expected = Result.success(listOf(concreteTransaction, concreteTransaction))
            given(networkDataSource.getTransactionsList()).willReturn(expected)

            //WHEN
            val actual = sut.getTransactions()

            //THEN
            assertEquals(expected, actual)

        }
    }

    @Test
    fun `Given OTHER results, When get transaction list, data is get from network datasource`() {
        runBlocking {
            //GIVEN
            val expected = Result.success(listOf(concreteOtherTransaction, concreteOtherTransaction))
            given(networkDataSource.getTransactionsList()).willReturn(expected)

            //WHEN
            val actual = sut.getTransactions()

            //THEN
            assertEquals(expected, actual)

        }
    }
}