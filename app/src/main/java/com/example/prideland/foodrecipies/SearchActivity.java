package com.example.prideland.foodrecipies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private TextView mfoodTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mfoodTextView = (TextView) findViewById(R.id.foodTextView);
        Intent intent = getIntent();
        String food = intent.getStringExtra("food");
        mfoodTextView.setText("Here is the recipie of your: " + food);
    }
}
