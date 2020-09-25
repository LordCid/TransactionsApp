package com.albertcid.transactionsapp.presentation.model

import org.joda.time.DateTime

data class TransactionUIModel (
    val id : Long,
    val date: DateTime,
    val amount: Double,
    val description: String
)