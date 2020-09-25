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
//
//    override fun isDateValid(date: String): Boolean {
//        var reFormat: String = Pattern.compile("d+|M+")
//            .matcher(Matcher.quoteReplacement(FORMAT))
//            .replaceAll("\\\\d{1,2}")
//        reFormat = Pattern.compile("y+").matcher(reFormat).replaceAll("\\\\d{4}")
//        if (Pattern.compile(reFormat).matcher(date).matches()) {
//
//            val sdf = DateFormat.getDateInstance() as SimpleDateFormat
//            sdf.applyPattern(FORMAT)
//            sdf.isLenient = false
//            try {
//                sdf.parse(date)
//            } catch (e: Exception) {
//                return false
//            }
//        }
//        return true
//    }
}