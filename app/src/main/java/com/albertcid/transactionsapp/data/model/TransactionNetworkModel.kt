package com.albertcid.transactionsapp.data.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class TransactionNetworkModel (
    @SerializedName("id")
    val id : Long? = null,
    @SerializedName("date")
    val date: DateTime? = null,
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("fee")
    val fee: Double? = null,
    @SerializedName("description")
    val description: String? = null
)