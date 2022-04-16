package com.hamza.ieeechallenge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.DailyMealDetail;

import java.util.List;

public class DetailedDailyMealAdapter extends RecyclerView.Adapter<DetailedDailyMealAdapter.ViewHolder> {

    List<DailyMealDetail> dailyMealDetailedList;
    Context context;
    public DetailedDailyMealAdapter(List<DailyMealDetail> dailyMealDetailedList , Context context) {
        this.dailyMealDetailedList = dailyMealDetailedList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_daily_meal_iitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(dailyMealDetailedList.get(position).getImage())
                .into(holder.imageMeal);
        holder.name.setText(dailyMealDetailedList.get(position).getName());
        holder.description.setText(dailyMealDetailedList.get(position).getRestaurant());
        holder.price.setText(dailyMealDetailedList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return dailyMealDetailedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
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

    public List<DailyMealDetail> getDailyMealList(){
        return dailyMealDetailedList;
    }
}
