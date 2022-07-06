package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.currencyconverter.fragments.CalculatorFragment
import com.example.currencyconverter.fragments.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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