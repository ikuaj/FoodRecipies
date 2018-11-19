package com.example.prideland.foodrecipies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prideland.foodrecipies.Services.Food;
import com.example.prideland.foodrecipies.adapter.MyRecipiesArrayAdapter;
import com.example.prideland.foodrecipies.adapter.RecipiesListAdapter;
import com.example.prideland.foodrecipies.models.Recipies;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentFood;

    public static final String TAG = SearchActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private RecipiesListAdapter mAdapter;

    public ArrayList<Recipies> recipies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String food = intent.getStringExtra("food");

        getRecipie(food);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentFood = mSharedPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);
        if (mRecentFood != null) {
            getRecipie(mRecentFood);
        }

    }

    private void getRecipie(String food) {
//        final Food food = new Food();

        Food.getFood(food, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) {
                 recipies = Food.processResults(response);

                SearchActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RecipiesListAdapter(getApplicationContext(), recipies);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
