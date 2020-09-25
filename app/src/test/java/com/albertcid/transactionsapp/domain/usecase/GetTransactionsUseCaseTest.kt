package com.albertcid.transactionsapp.domain.usecase

import com.albertcid.transactionsapp.*
import com.albertcid.transactionsapp.data.Repository
import com.albertcid.transactionsapp.domain.model.Transaction
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
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
    fun `Given concrete data, should return repository results, when invoked`() {
        runBlocking {
            //GIVEN
            val expected = listOf(concreteTransaction, concreteTransaction)
            given(repository.getTransactions()).willReturn(Result.success(expected))

            //WHEN
            val actual = sut.invoke()

            //THEN
            verify(repository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }

    }

    @Test
    fun `Given OTHER concrete data, should return repository results, when invoked`() {
        runBlocking {
            //GIVEN
            val expected = listOf(concreteOtherTransaction, concreteOtherTransaction)
            given(repository.getTransactions()).willReturn(Result.success(expected))

            //WHEN
            val actual = sut.invoke()

            //THEN
            verify(repository).getTransactions()
            assertEquals(Result.success(expected), actual)
        }

    }

}