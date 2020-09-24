package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.concreteOtherTransaction
import com.albertcid.transactionsapp.concreteOtherTransactionNetworModel
import com.albertcid.transactionsapp.concreteTransaction
import com.albertcid.transactionsapp.concreteTransactionNetworModel
import com.albertcid.transactionsapp.data.model.TransactionNetworkModel
import com.albertcid.transactionsapp.domain.model.Transaction
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers
import retrofit2.mock.Calls

class NetworkDataSourceTest {

    private lateinit var sut: NetworkDataSource
    private val apiService = mock<ApiService>()

    @Before
    fun setUp() {
        sut = NetworkDataSourceImpl(apiService)
    }

    @Test
    fun `When get group list, should invoke proper apiService function`() {
        runBlocking {
            givenNetworkTransactionsListResponseOK(emptyList())

            sut.getTransactionsList()

            verify(apiService).getTransactions()
        }
    }

    @Test
    fun `Given Success response, when get group list, then domain result success list model is returned`() {
        runBlocking {
            val expected = listOf(concreteTransaction, concreteTransaction)
            givenNetworkTransactionsListResponseOK(
                listOf(concreteTransactionNetworModel, concreteTransactionNetworModel)
            )

            val actual = sut.getTransactionsList()

            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Success response, when get OTHER group list, then domain result success list model is returned`() {
        runBlocking {
            val expected = listOf(concreteOtherTransaction, concreteOtherTransaction)
            givenNetworkTransactionsListResponseOK(
                listOf(concreteOtherTransactionNetworModel, concreteOtherTransactionNetworModel)
            )

            val actual = sut.getTransactionsList()

            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Failure response, when get group list, then return Result failure`() {
        runBlocking {
            givenNetworkTransactionsListResponsKO()

            val result = sut.getTransactionsList()

            assert(result.isFailure)
        }
    }


    private fun givenNetworkTransactionsListResponseOK(responseData: List<TransactionNetworkModel>) {
        given(apiService.getTransactions()).willReturn(Calls.response(responseData))
    }


    private fun givenNetworkTransactionsListResponsKO() {
        given(apiService.getTransactions()).willReturn(Calls.failure(mock()))
    }

}