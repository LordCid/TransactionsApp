package com.albertcid.transactionsapp.domain

import com.albertcid.transactionsapp.data.network.model.TransactionNetworkModel
import com.albertcid.transactionsapp.domain.model.Transaction
import com.albertcid.transactionsapp.presentation.model.TransactionUIModel
import org.joda.time.DateTime

fun TransactionNetworkModel.toTransactionModel() = Transaction(
    id = this.id ?:0,
    date = DateTime(this.date),
    fee = this.fee ?:0.0,
    amount = this.amount ?:0.0,
    description = this.description ?:""
)

fun Transaction.toTransactionUIModel() = TransactionUIModel(
    id = this.id,
    date = this.date,
    amount = this.amount + this.fee,
    description = this.description
)