package com.example.prideland.foodrecipies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prideland.foodrecipies.Services.Food;
import com.example.prideland.foodrecipies.adapter.MyRecipiesArrayAdapter;
import com.example.prideland.foodrecipies.models.Recipies;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.foodTextView) TextView mfoodTextView;
    @BindView(R.id.listView) ListView mListView;

    public ArrayList<Recipies> recipies = new ArrayList<>();
    private String[] foods = new String[] {"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole" };
    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

//        mListView = (ListView) findViewById(R.id.listView);
//        mfoodTextView = (TextView) findViewById(R.id.foodTextView);

        MyRecipiesArrayAdapter adapter = new MyRecipiesArrayAdapter(this, android.R.layout.simple_list_item_1, foods, cuisines);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(SearchActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String food = intent.getStringExtra("food");
        mfoodTextView.setText("Here is the recipie of your: " + food);
    }

    private void getRecipies(String recipi) {
        Food.getFood(recipi, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v("TAG", jsonData);
                        recipies = Food.ProcessResults(response);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    });
}
}
