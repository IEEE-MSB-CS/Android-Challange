package com.hamza.ieeechallenge.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.DailyMealDetialed;

import java.util.List;

public class DetailedDailyMeal  extends RecyclerView.Adapter<DetailedDailyMeal.ViewHolder> {
    List<DailyMealDetialed>dailyMealDetialeds;
    Context context;

    public DetailedDailyMeal(List<DailyMealDetialed> dailyMealDetialeds) {
        this.dailyMealDetialeds = dailyMealDetialeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_daily_meal_iitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageMeal.setImageResource(dailyMealDetialeds.get(position).getImage());
        holder.name.setText(dailyMealDetialeds.get(position).getName());
        holder.description.setText(dailyMealDetialeds.get(position).getDescription());
        holder.price.setText(dailyMealDetialeds.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return dailyMealDetialeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageMeal;
        TextView name, description , price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMeal = itemView.findViewById(R.id.detaild_image);
            name = itemView.findViewById(R.id.detailed_name);
            description = itemView.findViewById(R.id.detailed_des);
            price = itemView.findViewById(R.id.price_daily_meal);
        }
    }
}
