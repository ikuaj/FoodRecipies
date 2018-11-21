package com.example.prideland.foodrecipies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prideland.foodrecipies.Constants;
import com.example.prideland.foodrecipies.R;
import com.example.prideland.foodrecipies.RecipiesDetailActivity;
import com.example.prideland.foodrecipies.models.Recipies;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirebaseRecipiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseRecipiesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRecipies(Recipies recipies) {
        ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.foodImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.foodNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.get()
                .load(recipies.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(restaurantImageView);

        nameTextView.setText(recipies.getTitle());
        categoryTextView.setText(recipies.getRecipeId());
        ratingTextView.setText("Rating: " + recipies.getSocialRank() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Recipies> recipies = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_FOODS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recipies.add(snapshot.getValue(Recipies.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RecipiesDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("recipies", Parcels.wrap(recipies));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
