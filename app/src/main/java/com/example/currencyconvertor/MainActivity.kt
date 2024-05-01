package com.example.currencyconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.currencyconvertor.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.alphavantage.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
        val response = retrofit.getrates("CURRENCY_EXCHANGE_RATE", "usd", "pkr", "CK0Q4BBVE5N0KKJO")
        binding.convertBtn.setOnClickListener {
            response.enqueue(object : Callback<Response> {
                override fun onResponse(p0: Call<Response>, p1: retrofit2.Response<Response>) {
                    val responseBody = p1.body()
                    if (p1.isSuccessful && responseBody != null) {
                        val realtimeCurrencyExchangeRate = responseBody.RealtimeCurrencyExchange
                        val exchangeRate: String? = realtimeCurrencyExchangeRate?.to_CurrencyName
                        if (exchangeRate != null) {
                            Log.d("Taggg", exchangeRate)
                            binding.result.text = exchangeRate.toString()
                        } else {
                            // Handle case where exchange rate is null
                            Log.d("Taggg", "Exchange rate is null")
                        }
                    } else {
                        Toast.makeText(applicationContext, p1.message(), Toast.LENGTH_LONG).show()
                    }
                    return
                }

                override fun onFailure(p0: Call<Response>, p1: Throwable) {
                    Toast.makeText(applicationContext, p1.message, Toast.LENGTH_LONG).show()

                }

            })
        }

    }
}