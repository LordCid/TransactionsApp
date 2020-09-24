package com.albertcid.transactionsapp.data.model

import com.albertcid.transactionsapp.domain.model.Transaction
import org.joda.time.DateTime

fun TransactionNetworkModel.toTransactionModel() = Transaction(
    id = this.id ?:0,
    date = DateTime(this.date),
    fee = this.fee ?:0.0,
    amount = this.amount ?:0.0,
    description = this.description ?:""
)