package com.example.currencyconvertor

data class RealtimeCurrencyExchangeRate(
    val From_CurrencyCode: String,
    val From_CurrencyName: String,
    val To_CurrencyCode: String,
    val to_CurrencyName: String?,
    val ExchangeRate: Int,

)