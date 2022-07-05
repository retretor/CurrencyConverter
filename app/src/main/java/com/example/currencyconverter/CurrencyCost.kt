package com.example.currencyconverter

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi

class CurrencyCost {
    private var response: Response = Response()
    private var switcher: Boolean = false
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("UnsafeOptInUsageWarning")
    fun start() {
        if (switcher) {
            return
        }
        response = Response()
        response.fillArrays()
        switcher = true
    }

    fun calculateCurrency(currency1: String?, currency2: String?): Double {
        Log.d("CurrencyCost", "calculateCurrency")

        if (!isCurrencyNameInArray(currency2) && currency2 != "UAH") return 1.0

        return if (currency1 == "UAH") {
            val cost = response.getCurrencyValue(currency2!!)
            Log.d("CurrencyCost", "calculateCurrency cost: $cost")
            cost
        }
        else if(currency2 == "UAH") {
            val cost = 1 / response.getCurrencyValue(currency1!!)
            Log.d("CurrencyCost", "calculateCurrency cost: $cost")
            cost
        }
        else if(currency1 != "UAH" && currency2 != "UAH") {
            val cost = response.getCurrencyValue(currency2!!) / response.getCurrencyValue(currency1!!)
            cost
        } else {
            1.0
        }

    }

    fun calculateCurrencyPriv(currency1: String?, currency2: String?): Double {
        Log.d("CurrencyCost", "calculateCurrency")

        if (!isCurrencyNameInArrayPriv(currency2) && currency2 != "UAH") return 1.0

        return if (currency1 == "UAH") {
            val cost = response.getValueBuyPriv(currency2!!)
            Log.d("CurrencyCost", "calculateCurrency cost: $cost")
            cost
        }
        else if(currency2 == "UAH") {
            val cost = 1 / response.getValueSalePriv(currency1!!)
            Log.d("CurrencyCost", "calculateCurrency cost: $cost")
            cost
        }
        else if(currency1 != "UAH" && currency2 != "UAH") {
            val cost = response.getValueBuyPriv(currency2!!) / response.getValueSalePriv(currency1!!)
            cost
        } else {
            1.0
        }

    }

    private fun isCurrencyNameInArray(currency: String?): Boolean {
        if (response.getCurrencyName(currency!!) == "") {
            return false
        } else {
            return true
        }
    }
    private fun isCurrencyNameInArrayPriv(currency: String?): Boolean {
        if (response.getCcPriv(currency!!) == "") {
            return false
        } else {
            return true
        }
    }
}