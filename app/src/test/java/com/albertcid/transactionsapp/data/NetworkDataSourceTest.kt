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
            //GIVEN
            givenNetworkTransactionsListResponseOK(emptyList())

            //WHEN
            sut.getTransactionsList()

            //THEN
            verify(apiService).getTransactions()
        }
    }

    @Test
    fun `Given Success response, when get group list, then result success list model is returned`() {
        runBlocking {
            //GIVEN
            val expected = listOf(concreteTransaction, concreteTransaction)
            givenNetworkTransactionsListResponseOK(
                listOf(concreteTransactionNetworModel, concreteTransactionNetworModel)
            )

            //WHEN
            val actual = sut.getTransactionsList()

            //THEN
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Success response, when get OTHER group list, then result success list model is returned`() {
        runBlocking {
            //GIVEN
            val expected = listOf(concreteOtherTransaction, concreteOtherTransaction)
            givenNetworkTransactionsListResponseOK(
                listOf(concreteOtherTransactionNetworModel, concreteOtherTransactionNetworModel)
            )

            //WHEN
            val actual = sut.getTransactionsList()

            //THEN
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given some transaction results with malformed Date, then these resutls wont be returned`() {
        runBlocking {
            //GIVEN
            val expected = listOf(concreteTransaction, concreteOtherTransaction)
            givenNetworkTransactionsListResponseOK(
                listOf(
                    concreteTransactionNetworModel,
                    concreteTransactionNetworModelMalformedDate,
                    concreteOtherTransactionNetworModel
                )
            )

            //WHEN
            val actual = sut.getTransactionsList()

            //THEN
            assertEquals(Result.success(expected), actual)
        }
    }

    @Test
    fun `Given Failure response, when get group list, then return Result failure`() {
        runBlocking {
            //GIVEN
            givenNetworkTransactionsListResponsKO()

            //WHEN
            val result = sut.getTransactionsList()

            //THEN
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