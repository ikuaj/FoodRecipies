package com.example.prideland.foodrecipies.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRecipiesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mFoods;
    private String[] mCuisines;

    public MyRecipiesArrayAdapter(Context mContext, int resource, String[] mFoods, String[] mCuisines) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFoods = mFoods;
        this.mCuisines = mCuisines;
    }

    @Override
    public Object getItem(int position) {
        String food = mFoods[position];
        String cuisine = mCuisines[position];
        return String.format("%s \nServes great: %s", food, cuisine);
    }

    @Override
    public int getCount() {
        return mFoods.length;
    }
}
