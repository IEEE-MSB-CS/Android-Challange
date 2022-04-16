package com.hamza.ieeechallenge.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.DailyMeal;

import java.util.List;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.ViewHolder> {

    List<DailyMeal> dailyMealList;
    View view  ;

    public DailyMealAdapter(List<DailyMeal> dailyMealList, View view) {
        this.dailyMealList = dailyMealList;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageDaily.setImageResource(dailyMealList.get(position).getImage());
        holder.name.setText(dailyMealList.get(position).getName());
        holder.description.setText(dailyMealList.get(position).getDescription());

        holder.itemView.setOnClickListener(v -> navigateToDailyMealDetailFragment(position));
    }

    @Override
    public int getItemCount() {
        return dailyMealList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageDaily;
        TextView name, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDaily = itemView.findViewById(R.id.image_item_daily_meal);
            name = itemView.findViewById(R.id.name_meal_daily);
            description = itemView.findViewById(R.id.description);
        }
    }

    private void navigateToDailyMealDetailFragment(int position) {
        final Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.TYPE, dailyMealList.get(position).getType());
        Navigation.findNavController(view).navigate(R.id.action_nav_daily_meal_to_dailyMealDetailFragment , bundle);

    }
}
