package com.example.currencyconverter

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.*
import org.json.JSONArray
import java.net.URL
import java.util.*

class Response {
    //API response
    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val day = c.get(Calendar.DAY_OF_MONTH)

    private val url: String = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json"
    private val url2: String = "https://api.privatbank.ua/p24api/exchange_rates?json&date=$day.$month.$year"

    //NBU data
    private var currencys_names_nbu = mutableListOf<String>()
    private var currencys_values_nbu = mutableListOf<Double>()
    private var currencys_cc_nbu = mutableListOf<String>()

    //Private data
    private var currencys_names_priv = mutableListOf<String>()
    private var currencys_value_buy_priv = mutableListOf<Double>()
    private var currencys_value_sale_priv = mutableListOf<Double>()
    private var currencys_cc_priv = mutableListOf<String>()

    //Get data

    fun getCurrencyName(currency_сс: String): String {
        var currency_name: String = ""
        for (i in 0 until currencys_cc_nbu.size) {
            if (currencys_cc_nbu[i].equals(currency_сс)) {
                currency_name = currencys_names_nbu[i]
            }
        }
        return currency_name
    }

    fun getCurrencyValue(currency_сс: String): Double {
        var currency_value: Double = 0.0
        for (i in 0 until currencys_cc_nbu.size) {
            if (currencys_cc_nbu[i].equals(currency_сс)) {
                currency_value = currencys_values_nbu[i]
            }
        }
        Log.d("Currency value", currency_value.toString())
        return currency_value
    }

    fun getCcPriv(currency_сс: String): String {
        var currency_cc: String = ""
        for (i in 0 until currencys_cc_priv.size) {
            if (currencys_cc_priv[i].equals(currency_сс)) {
                currency_cc = currencys_cc_priv[i]
            }
        }
        return currency_cc
    }

    fun getValueBuyPriv(currency_сс: String): Double {
        var currency_value: Double = 0.0
        for (i in 0 until currencys_cc_priv.size) {
            if (currencys_cc_priv[i].equals(currency_сс)) {
                currency_value = currencys_value_buy_priv[i]
            }
        }
        return currency_value
    }
    fun getValueSalePriv(currency_сс: String): Double {
        var currency_value: Double = 0.0
        for (i in 0 until currencys_cc_priv.size) {
            if (currencys_cc_priv[i].equals(currency_сс)) {
                currency_value = currencys_value_sale_priv[i]
            }
        }
        return currency_value
    }


    //Fill arrays with data
    @DelicateCoroutinesApi
    fun fillArrays() {
        CoroutineScope(Dispatchers.IO).launch {
            async { getResponses()
                    getResponsesPrivat()}.await()
        }
    }

    suspend fun getResponsesPrivat() {
        var response = URL(url2).readText()
        response = response.substring(response.indexOf("["), response.indexOf("]") + 1)
        val jsonArray2 = JSONArray(response)

        for (i in 1 until jsonArray2.length()) {
            val jsonObject = jsonArray2.getJSONObject(i)
            if(jsonObject.has("saleRate") && jsonObject.has("purchaseRate"))
            {
                currencys_names_priv.add(jsonObject.getString("currency"))
                currencys_value_buy_priv.add(jsonObject.getDouble("purchaseRate"))
                currencys_value_sale_priv.add(jsonObject.getDouble("saleRate"))
                currencys_cc_priv.add(jsonObject.getString("currency"))
            }
        }
    }

    suspend fun getResponses() {
        val response = URL(url).readText()
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            currencys_names_nbu.add(jsonObject.getString("txt"))
            currencys_values_nbu.add(jsonObject.getDouble("rate"))
            currencys_cc_nbu.add(jsonObject.getString("cc"))
            Log.d("Currency_сс $i: ", currencys_cc_nbu[i])
        }
    }

    //Check internet connection
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
