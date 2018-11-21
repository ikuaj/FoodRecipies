package com.example.prideland.foodrecipies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.prideland.foodrecipies.adapter.FirebaseRecipiesViewHolder;
import com.example.prideland.foodrecipies.models.Recipies;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedFoodListActivity extends AppCompatActivity {
    private DatabaseReference mRecipiesReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mRecipiesReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_FOODS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Recipies, FirebaseRecipiesViewHolder>
                (Recipies.class, R.layout.serch_list_item, FirebaseRecipiesViewHolder.class,
                        mRecipiesReference) {

            @Override
            protected void populateViewHolder(FirebaseRecipiesViewHolder viewHolder,
                                              Recipies model, int position) {
                viewHolder.bindRecipies(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
