package com.albertcid.transactionsapp.domain


import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class DateCheckerTest {

    private lateinit var sut: DateChecker

    @Before
    fun setUp() {
        sut = DateCheckerImpl()
    }

    @Test
    fun `When malformed date input return false`() {
        //GIVEN
        val date = "2018--11T11:31:27.000Z"

        //WHEN
        val actual = sut.isDateValid(date)

        //THEN
        assertFalse(actual)
    }

    @Test
    fun `When correct date input return true`() {
        //GIVEN
        val date = "2018-07-14T16:54:27.000Z"

        //WHEN
        val actual = sut.isDateValid(date)

        //THEN
        assertTrue(actual)
    }
}