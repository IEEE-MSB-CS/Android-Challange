package com.hamza.ieeechallenge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.ActivityFoodDetailBinding;

public class FoodDetailActivity extends AppCompatActivity {
    private ActivityFoodDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvTitle.setText(getIntent().getStringExtra("title"));
        binding.tvRestaurantName.setText(getIntent().getStringExtra("restaurantName"));
        binding.tvPrice.setText(getIntent().getStringExtra("price"));
        binding.tvRating.setText(String.valueOf(getIntent().getDoubleExtra("rating",0)));
        binding.tvDescription.setText(getIntent().getStringExtra("description"));
        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .into(binding.ivFood);
        binding.ratingBar.setRating((float) getIntent().getDoubleExtra("rating",0));
    }
}