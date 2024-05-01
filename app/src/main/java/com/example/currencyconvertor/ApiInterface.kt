package com.example.currencyconvertor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("query")
    fun getrates(
        @Query("function") funtion: String,
        @Query("from_currency") from: String,
        @Query("to_currency") to: String,
        @Query("apikey") api: String

    ): Call<Response>
}