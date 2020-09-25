package com.albertcid.transactionsapp.domain

interface DateChecker {
    fun isDateValid(date: String): Boolean
}