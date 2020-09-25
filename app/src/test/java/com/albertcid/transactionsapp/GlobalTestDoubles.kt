package com.albertcid.transactionsapp

import com.albertcid.transactionsapp.data.network.model.TransactionNetworkModel
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.presentation.model.TransactionUIModel
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

val firstTransaction = getTransactionByDate("2018-07-11T22:49:24.000Z")
val secondTransaction = getTransactionByDate("2018-07-14T16:54:27.000Z")
val thirdTransaction =  getTransactionByDate("2018-07-24T18:10:10.000Z")
val fourthTransaction = getTransactionByDate("2018-07-29T17:56:43.000Z")

val firstTransactionUI = getUIModelTransactionByDate("2018-07-11T22:49:24.000Z")
val secondTransactionUI = getUIModelTransactionByDate("2018-07-14T16:54:27.000Z")
val thirdTransactionUI = getUIModelTransactionByDate("2018-07-24T18:10:10.000Z")
val fourthTransactionUI = getUIModelTransactionByDate("2018-07-29T17:56:43.000Z")

val firstOtherTransaction = getOtherTransactionByDate("2018-07-11T22:49:24.000Z")
val secondOtherTransaction = getOtherTransactionByDate("2018-07-14T16:54:27.000Z")
val thirdOtherTransaction = getOtherTransactionByDate("2018-07-24T18:10:10.000Z")
val fourthOtherTransaction = getOtherTransactionByDate("2018-07-29T17:56:43.000Z")

val firstOtherTransactionUI = getOtherUIModelTransactionByDate("2018-07-11T22:49:24.000Z")
val secondOtherTransactionUI = getOtherUIModelTransactionByDate("2018-07-14T16:54:27.000Z")
val thirdOtherTransactionUI= getOtherUIModelTransactionByDate("2018-07-24T18:10:10.000Z")
val fourthOtherTransactionUI= getOtherUIModelTransactionByDate("2018-07-29T17:56:43.000Z")

private fun getTransactionByDate(date: String) = Transaction(
    id = 123,
    date = DateTime(date),
    fee = -35.06,
    amount = -234.56,
    description = "description"
)

private fun getOtherTransactionByDate(date: String) = Transaction(
    id = 4567,
    date = DateTime(date),
    amount = -234.56,
    description = "description"
)

private fun getUIModelTransactionByDate(date: String) = TransactionUIModel(
    id = 123,
    date = DateTime(date),
    amount = -269.62,
    description = "description"
)

private fun getOtherUIModelTransactionByDate(date: String) = TransactionUIModel(
    id = 4567,
    date = DateTime(date),
    amount = -234.56,
    description = "description"
)