package com.example.prideland.foodrecipies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prideland.foodrecipies.R;
import com.example.prideland.foodrecipies.models.Recipies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipiesListAdapter extends RecyclerView.Adapter<RecipiesListAdapter.RecipiesViewHolder> {
    private ArrayList<Recipies> mRecipies = new ArrayList<>();
    private Context mContext;

    public RecipiesListAdapter(Context context, ArrayList<Recipies> recipies) {
        mContext = context;
        mRecipies = recipies;
    }

    @Override
    public  RecipiesListAdapter.RecipiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serch_list_item, parent, false);
        RecipiesViewHolder viewHolder = new RecipiesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipiesListAdapter.RecipiesViewHolder holder, int position) {
        holder.bindRecipies(mRecipies.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipies.size();
    }

    public class RecipiesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foodImageView) ImageView mfoodImageView;
        @BindView(R.id.foodNameTextView) TextView mfoodNameTextView;
        @BindView(R.id.categoryTextView) TextView mcategoryTextView;
        @BindView(R.id.ratingTextView) TextView mratingTextView;

        private Context mContext;

        public RecipiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRecipies(Recipies recipies) {
            Picasso.get().load(recipies.getImageUrl()).into(mfoodImageView);
            mfoodNameTextView.setText(recipies.getTitle());
            mcategoryTextView.setText(recipies.getPublisher());
            mratingTextView.setText("Rating: " + recipies.getSocialRank() + "/5");
        }
    }
}
