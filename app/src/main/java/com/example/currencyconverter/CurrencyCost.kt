package com.example.currencyconverter

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi

class CurrencyCost {
    private var response: Response = Response()
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("UnsafeOptInUsageWarning")
    fun start() {
        response = Response()
        response.fillArrays()
    }

    fun calculateCurrency(currency1: String?, currency2: String?, value: String): Double {
        Log.d("CurrencyCost", "calculateCurrency")
        return if (currency2 == "") {
            val value1 = value.toDouble()
            val cost = response!!.getCurrencyValue(currency1!!)
            value1 / cost
        } else {
            1.0
        }
    }
}