package com.example.currencyconverter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.currencyconverter.CurrencyCost
import com.example.currencyconverter.R
import java.text.DecimalFormat

class CalculatorFragment : Fragment(), View.OnClickListener {
    private var currency_1_value: EditText? = null
    private var currency_2_value: TextView? = null
    private var choice_rates: Spinner? = null
    private var choice_currency_1: Spinner? = null
    private var choice_currency_2: Spinner? = null
    private var temp_spinner: Int = 0


    private var currencyCost: CurrencyCost? = null

    private var bankInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currencyCost = CurrencyCost()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.convert_btn -> {
                currencyCost?.start()

                currency_1_value = view?.findViewById(R.id.currency_1_value)
                currency_2_value = view?.findViewById(R.id.currency_2_value)

                choice_rates = view?.findViewById(R.id.choice_rates)
                choice_currency_1 = view?.findViewById(R.id.currency_1)
                choice_currency_2 = view?.findViewById(R.id.currency_2)
                bankInfo = view?.findViewById(R.id.bankInfo)


                val currency_1_value_string = currency_1_value?.text.toString()
                Log.d("Currency_1_value", currency_1_value_string)
                val currency_1_string = choice_currency_1?.selectedItem.toString()
                Log.d("Currency_1", currency_1_string)
                val currency_2_string = choice_currency_2?.selectedItem.toString()
                Log.d("Currency_2", currency_2_string)
                var rates_string = choice_rates?.selectedItem.toString()
                Log.d("Rates", rates_string)

                var cost: Double = 1.0
                if(rates_string == "NBU") {
                    cost =
                        currencyCost?.calculateCurrency(currency_1_string, currency_2_string)!!
                            .toDouble()
                } else {
                    cost =
                        currencyCost?.calculateCurrencyPriv(currency_1_string, currency_2_string)!!
                            .toDouble()
                }
                if (cost < 1) {
                    bankInfo?.text = "Course: " + String.format("%.2f", 1 / cost)
                }
                else {
                    bankInfo?.text = "Course: " + String.format("%.2f", cost)
                }


                val currency_2_value_double = currency_1_value_string.toDouble() / cost

                // set the value of currency_2_value_double to the TextView currency_2_value
                currency_2_value?.text = String.format("%.2f", currency_2_value_double)

                Log.d("Currency", currency_2_value_double.toString())
            }
            R.id.inverse_button -> {
                swapCurrencies()
            }
        }
    }
    private fun swapCurrencies() {
        choice_currency_1 = view?.findViewById(R.id.currency_1)
        choice_currency_2 = view?.findViewById(R.id.currency_2)
        // swap the values of the two spinners
        temp_spinner = choice_currency_1!!.selectedItemPosition

        choice_currency_1?.setSelection(choice_currency_2!!.selectedItemPosition)
        choice_currency_2?.setSelection(temp_spinner)

        Log.d("Swap", "Swapped")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_calculator, container, false)
        val convert_button: Button = view.findViewById(R.id.convert_btn)
        val inverse_button: ImageButton = view.findViewById(R.id.inverse_button)
        convert_button.setOnClickListener(this)
        inverse_button.setOnClickListener(this)

        return view
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