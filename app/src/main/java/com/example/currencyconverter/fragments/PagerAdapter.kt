package com.example.currencyconverter.fragments;

import static java.util.Objects.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Objects;

public class PagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 2;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CalculatorFragment();
            default:
                return new RatesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}