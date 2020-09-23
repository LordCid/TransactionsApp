package com.albertcid.transactionsapp

import com.albertcid.transactionsapp.domain.model.Transaction
import org.joda.time.DateTime


val concreteTransaction = Transaction(
    id = 123,
    date = DateTime(),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)

val concreteOtherTransaction = Transaction(
    id = 456,
    date = DateTime(),
    amount = -234.56,
    description = "description"
)