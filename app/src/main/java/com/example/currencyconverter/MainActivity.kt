package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.currencyconverter.fragments.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    var currency_1_value: EditText? = null
    var convert_button: Button? = null
    var currency_2_value: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convert_button = findViewById(R.id.convert_btn)
        currency_1_value = findViewById(R.id.currency_1_value)
        currency_2_value = findViewById(R.id.currency_2_value)

        val choice_rates = findViewById<Spinner>(R.id.choice_rates)
        val choice_currency_1 = findViewById<Spinner>(R.id.currency_1)
        val choice_currency_2 = findViewById<Spinner>(R.id.currency_2)

        Draw()

    }

    private fun Draw() {
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = PagerAdapter(this)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tabLayoutMediator = TabLayoutMediator(
            tabLayout, viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Calculator"
                    tab.setIcon(R.drawable.ic_exchange)
                    Log.d("TAG", "Calculator")
                }
                1 -> {
                    tab.text = "Rates"
                    tab.setIcon(R.drawable.ic_currency_list)
                    Log.d("TAG", "Rates")
                }
            }
        }
        tabLayoutMediator.attach()
    }
}