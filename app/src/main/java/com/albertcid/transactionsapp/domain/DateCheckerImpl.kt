package com.albertcid.transactionsapp.domain

import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject


const val FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
class DateCheckerImpl @Inject constructor(): DateChecker {
    override fun isDateValid(date: String): Boolean {
       val sdf = DateFormat.getDateInstance() as SimpleDateFormat
        sdf.applyPattern(FORMAT)
        sdf.isLenient = false
        return try {
            sdf.parse(date)
            true
        } catch (exception: Exception) {
            false
        }
    }
}