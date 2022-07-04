package com.example.currencyconverter.fragments

import android.os.Bundle
import android.util.Log
import com.example.currencyconverter.fragments.CalculatorFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.currencyconverter.CurrencyCost
import com.example.currencyconverter.R

class CalculatorFragment : Fragment() {
    private var currency_1_value: EditText? = null
    private var convert_button: Button? = null
    private var currency_2_value: TextView? = null
    private var choice_rates: Spinner? = null
    private var choice_currency_1: Spinner? = null
    private var choice_currency_2: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currency_1_value = view?.findViewById(R.id.currency_1_value)
        convert_button = view?.findViewById(R.id.convert_btn)
        currency_2_value = view?.findViewById(R.id.currency_2_value)

        choice_rates = view?.findViewById(R.id.choice_rates)
        choice_currency_1 = view?.findViewById(R.id.currency_1)
        choice_currency_2 = view?.findViewById(R.id.currency_2)

        val currencyCost = CurrencyCost()
        currencyCost.start()

        convert_button?.setOnClickListener {
            val currency_1_value_string = currency_1_value?.text.toString()
            val currency_1_string = choice_currency_1?.selectedItem.toString()
            val currency_2_string = choice_currency_2?.selectedItem.toString()
            var rates_string = choice_rates?.selectedItem.toString()

            val currency_2_value_double = currencyCost.calculateCurrency(currency_1_string, currency_2_string, currency_1_value_string).toDouble()

            currency_2_value?.text = currency_2_value.toString()
            Log.d("Currency", currency_2_value_double.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): CalculatorFragment {
            val fragment = CalculatorFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}