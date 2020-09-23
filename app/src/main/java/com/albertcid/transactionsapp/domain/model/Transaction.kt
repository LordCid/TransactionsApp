package com.albertcid.transactionsapp.domain.model

import org.joda.time.DateTime

data class Transaction(
    val id : Long,
    val date: DateTime,
    val amount: Double,
    val fee: Double = 0.0,
    val description: String
)