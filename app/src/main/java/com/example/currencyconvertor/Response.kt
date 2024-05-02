package com.example.currencyconvertor

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("Realtime Currency Exchange Rate")
    val RealtimeCurrencyExchange : RealtimeCurrencyExchangeRate


)