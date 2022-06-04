package com.example.customitemviewtest;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class PagerAdapter extends FragmentStateAdapter {
    @NonNull
    private static int NUM_ITEMS=200;
    private static int CURRENT = 100;
    public PagerAdapter(FragmentActivity fa) {
        super(fa);
    }
    @Override
    public Fragment createFragment(int position) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int idx_dif = month - (CURRENT % 12);
        int year_std = (CURRENT + idx_dif) / 12;
        int year_dif = ((position +  idx_dif) / 12) - year_std;

        MonthFragment monthFragment = new MonthFragment(year+year_dif,(position+idx_dif)%12);
        monthFragment.newInstance(year+year_dif,(position+idx_dif)%12);
        Log.i("hey", "YEAR, MONTH: " + (year+year_dif) + ", " +((position+idx_dif)%12));
        return monthFragment;
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
