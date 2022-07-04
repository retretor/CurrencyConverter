package com.example.currencyconverter.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.currencyconverter.fragments.CalculatorFragment
import com.example.currencyconverter.fragments.RatesFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CalculatorFragment()
            else -> RatesFragment()
        }
    }

    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    companion object {
        private const val NUM_ITEMS = 2
    }
}