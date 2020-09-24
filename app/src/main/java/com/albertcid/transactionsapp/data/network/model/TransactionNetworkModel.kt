package com.albertcid.transactionsapp.data.network.model

import com.google.gson.annotations.SerializedName

data class TransactionNetworkModel (
    @SerializedName("id")
    val id : Long? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("fee")
    val fee: Double? = null,
    @SerializedName("description")
    val description: String? = null
)