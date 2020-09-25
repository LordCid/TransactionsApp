package com.albertcid.transactionsapp

import com.albertcid.transactionsapp.data.network.model.TransactionNetworkModel
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.presentation.transaction.model.TransactionUIModel
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

val concreteTransactionNetworModelMalformedDate = TransactionNetworkModel(
    id = 456,
    date = "2018--11T11:31:27.000Z",
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

val firstTransaction = Transaction(
    id = 123,
    date = DateTime("2018-07-11T22:49:24.000Z"),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)
val secondTransaction = Transaction(
    id = 34,
    date = DateTime("2018-07-14T16:54:27.000Z"),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)
val thirdTransaction =  Transaction(
    id = 3478,
    date = DateTime("2018-07-24T18:10:10.000Z"),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)


val firstTransactionUI = TransactionUIModel(
    id = 123,
    date = DateTime("2018-07-11T22:49:24.000Z"),
    amount = -269.62,
    description = "description"
)
val secondTransactionUI = TransactionUIModel(
    id = 34,
    date = DateTime("2018-07-14T16:54:27.000Z"),
    amount = -269.62,
    description = "description"
)
val thirdTransactionUI = TransactionUIModel(
    id = 3478,
    date = DateTime("2018-07-24T18:10:10.000Z"),
    amount = -269.62,
    description = "description"
)

val firstOtherTransaction =  Transaction(
    id = 4567,
    date = DateTime("2018-07-11T22:49:24.000Z"),
    amount = -234.56,
    description = "description"
)
val secondOtherTransaction = Transaction(
    id = 987,
    date = DateTime("2018-07-14T16:54:27.000Z"),
    amount = -234.56,
    description = "description"
)
val thirdOtherTransaction =  Transaction(
    id = 432,
    date = DateTime("2018-07-24T18:10:10.000Z"),
    amount = -234.56,
    description = "description"
)

val firstOtherTransactionUI = TransactionUIModel(
    id = 4567,
    date = DateTime("2018-07-11T22:49:24.000Z"),
    amount = -234.56,
    description = "description"
)
val secondOtherTransactionUI = TransactionUIModel(
    id = 987,
    date = DateTime("2018-07-14T16:54:27.000Z"),
    amount = -234.56,
    description = "description"
)
val thirdOtherTransactionUI = TransactionUIModel(
    id = 432,
    date = DateTime("2018-07-24T18:10:10.000Z"),
    amount = -234.56,
    description = "description"
)


fun getTransactionByDate(date: String) = Transaction(
    id = 123,
    date = DateTime(date),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)

fun getOtherTransactionByDate(date: String) = Transaction(
    id = 425,
    date = DateTime(date),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)

fun getUIModelTransactionByDate(date: String) = TransactionUIModel(
    id = 123,
    date = DateTime(date),
    amount = -269.62,
    description = "description"
)

fun getOtherUIModelTransactionByDate(date: String) = TransactionUIModel(
    id = 425,
    date = DateTime(date),
    amount = -269.62,
    description = "description"
)
