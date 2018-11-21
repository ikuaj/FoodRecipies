package com.example.prideland.foodrecipies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//
//    private DatabaseReference mSearchedFoodReference;

    @BindView(R.id.findFoodbutton) Button mfindFoodButton;
//    @BindView(R.id.foodEditText) EditText mfoodEditText;
    @BindView(R.id.titleTextView) TextView mtitleTextView;
    @BindView(R.id.savedRecipiesButton) Button msavedRecipiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mSearchedFoodReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_FOODS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface mali = Typeface.createFromAsset(getAssets(), "fonts/GrandHotel-Regular.otf");
        mtitleTextView.setTypeface(mali);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mfindFoodButton.setOnClickListener(this);
        msavedRecipiesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mfindFoodButton) {
            Intent intent = new Intent(MainActivity.this, SearchListActivity.class);
            startActivity(intent);
        }
            //            Food.getFood(food, new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    Log.d("Response",response.body().string());
//                }
//            });
            if (v == msavedRecipiesButton) {
                Intent intent = new Intent(MainActivity.this, SavedFoodListActivity.class);
                startActivity(intent);
            }
        }
    }

//    private void saveFoodToFirebase(String food) {
//        mSearchedFoodReference.push().setValue(food);
//    }


//    private void addToSharedPreferences(String food) {
//        mEditor.putString(Constants.PREFERENCES_FOOD_KEY, food).apply();
//    }

