package com.example.currencyconvertor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("query")
    fun getrates(
        @Query("function") function: String,
        @Query("from_currency") fromCurrency: String,
        @Query("to_currency") toCurrency: String,
        @Query("apikey") apiKey: String

    ): Call<Response>
}