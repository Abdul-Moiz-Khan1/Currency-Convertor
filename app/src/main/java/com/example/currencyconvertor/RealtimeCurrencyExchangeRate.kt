package com.example.currencyconvertor

import com.google.gson.annotations.SerializedName

data class RealtimeCurrencyExchangeRate(
    @SerializedName("1. From_Currency Code")
    val From_CurrencyCode: String,
    @SerializedName("2. From_Currency Name")
    val From_CurrencyName: String,
    @SerializedName("3. To_Currency Code")
    val To_CurrencyCode: String,
    @SerializedName("4. To_Currency Name")
    val to_CurrencyName: String?,
    @SerializedName("5. Exchange Rate")
    val ExchangeRate: String,

)