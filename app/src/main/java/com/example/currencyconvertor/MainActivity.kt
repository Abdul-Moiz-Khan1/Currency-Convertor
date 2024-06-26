package com.example.currencyconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.currencyconvertor.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.time.times

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lottiemain.visibility = View.INVISIBLE

        var from = "inr"
        var to = "usd"
        var exchangeRate = "1"


        binding.convertButton.setOnClickListener {

            binding.lottiemain.visibility = View.VISIBLE
            binding.lottiemain.setAnimation(R.raw.money_rain)

            from = binding.to.text.toString()
            to = binding.from.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.alphavantage.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface::class.java)
            val response = retrofit.getrates("CURRENCY_EXCHANGE_RATE", from, to, "3LONZJDNI5HT7MGD")
            response.enqueue(object : Callback<Response> {
                override fun onResponse(p0: Call<Response>, p1: retrofit2.Response<Response>) {
                    val responseBody = p1.body()
                    if (p1.isSuccessful && responseBody != null) {
                        binding.lottiemain.playAnimation()
                        exchangeRate =
                            responseBody.RealtimeCurrencyExchange?.ExchangeRate.toString()
                        if (exchangeRate != null) {
                            Log.d("Taggg", exchangeRate)
                            binding.conversionRate.text = "Conversion rate: ${exchangeRate}"
                            val amount = binding.amount.text.toString()
                            showconversion(exchangeRate, amount)
                        } else {
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
//            binding.lottiemain.visibility = View.INVISIBLE
        }
    }

    private fun showconversion(rate: String, amount: String) {

        val fin = if (amount.isNotEmpty()) {
            amount.toDouble()
        } else {
            0.0
        }
        Toast.makeText(this, fin.toString(), Toast.LENGTH_LONG).show()

        binding.result.text = (rate.toDouble() * fin).toString()

    }

}