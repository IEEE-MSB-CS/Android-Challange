package com.hamza.ieeechallenge.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.auth.User;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.activities.FoodDetailActivity;
import com.hamza.ieeechallenge.model.CartModule;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.ui.MyCartFragment;

import java.util.ArrayList;
import java.util.UUID;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    Context context;
    ArrayList<Food> foodList;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public FoodAdapter(Context context, ArrayList<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(foodList.get(position).getImage())
                .into(holder.image);
        holder.title.setText(foodList.get(position).getTitle());
        holder.restaurantName.setText(foodList.get(position).getRestaurant());
        holder.price.setText(foodList.get(position).getPrice());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra("title",foodList.get(position).getTitle());
                intent.putExtra("image",foodList.get(position).getImage());
                intent.putExtra("price",foodList.get(position).getPrice());
                intent.putExtra("rating",foodList.get(position).getRating());
                intent.putExtra("restaurantName",foodList.get(position).getRestaurant());
                intent.putExtra("description",foodList.get(position).getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyCartFragment.class);
                intent.putExtra("title",foodList.get(position).getTitle());
                intent.putExtra("image",foodList.get(position).getImage());
                intent.putExtra("price",foodList.get(position).getPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (foodList == null)? 0 : foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,restaurantName,rating,price;
        Button add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ver_image);
            title = itemView.findViewById(R.id.productName);
            restaurantName = itemView.findViewById(R.id.tv_restaurant_name);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add_tocart);
        }
    }
}
