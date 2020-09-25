package com.albertcid.transactionsapp.data

import com.albertcid.transactionsapp.*
import com.albertcid.transactionsapp.data.network.ApiService
import com.albertcid.transactionsapp.data.network.NetworkDataSource
import com.albertcid.transactionsapp.data.network.NetworkDataSourceImpl
import com.albertcid.transactionsapp.data.network.model.TransactionNetworkModel
import com.albertcid.transactionsapp.domain.DateCheckerImpl
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import retrofit2.mock.Calls

class NetworkDataSourceTest {

    private lateinit var sut: NetworkDataSource
    private val apiService = mock<ApiService>()
    private val dateValidator = DateCheckerImpl()

    @Before
    fun setUp() {
        sut = NetworkDataSourceImpl(apiService, dateValidator)
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
    fun `Given Success response, when get group list, then result success list model is returned`() {
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
    fun `Given Success response, when get OTHER group list, then result success list model is returned`() {
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
    fun `Given some transaction results with malformed Date, then these resutls wont be returned`() {
        runBlocking {
            val expected = listOf(concreteTransaction, concreteOtherTransaction)
            givenNetworkTransactionsListResponseOK(
                listOf(
                    concreteTransactionNetworModel,
                    concreteTransactionNetworModelMalformedDate,
                    concreteOtherTransactionNetworModel
                )
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