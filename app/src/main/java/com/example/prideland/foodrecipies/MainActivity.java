package com.example.prideland.foodrecipies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mfindFoodButton;
    private EditText mfoodEditText;
    private TextView mtitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtitleTextView = (TextView) findViewById(R.id.titleTextView);
        Typeface mali = Typeface.createFromAsset(getAssets(), "fonts/GrandHotel-Regular.otf");
        mtitleTextView.setTypeface(mali);
        mfoodEditText = (EditText) findViewById(R.id.foodEditText);
        mfindFoodButton = (Button) findViewById(R.id.findFoodbutton);
        mfindFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                String food = mfoodEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("food", food);
                startActivity(intent);
            }
        });
    }
}
