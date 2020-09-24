package com.albertcid.transactionsapp

import com.albertcid.transactionsapp.data.network.model.TransactionNetworkModel
import com.albertcid.transactionsapp.domain.model.Transaction
import org.joda.time.DateTime

val concreteTransactionNetworModel = TransactionNetworkModel(
    id = 123,
    date = "2018-07-11T22:49:24.000Z",
    fee = -35.06,
    amount = -234.56,
    description = "description"
)

val concreteOtherTransactionNetworModel = TransactionNetworkModel(
    id = 456,
    date = "2018-07-11T22:49:24.000Z",
    amount = -234.56,
    description = "description"
)


val concreteTransaction = Transaction(
    id = 123,
    date = DateTime("2018-07-11T22:49:24.000Z"),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)

val concreteOtherTransaction = Transaction(
    id = 456,
    date = DateTime("2018-07-11T22:49:24.000Z"),
    amount = -234.56,
    description = "description"
)