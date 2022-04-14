package com.hamza.ieeechallenge.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.activities.DetailsDailyMealActivity;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.DailyMealModule;

import java.util.List;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.ViewHolder> {

    List<DailyMealModule> dailyMealModuleList;
    Context context;

    public DailyMealAdapter(List<DailyMealModule> dailyMealModuleList, Context context) {
        this.dailyMealModuleList = dailyMealModuleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageDaily.setImageResource(dailyMealModuleList.get(position).getImage());
        holder.name.setText(dailyMealModuleList.get(position).getName());
        holder.description.setText(dailyMealModuleList.get(position).getDescription());

        holder.itemView.setOnClickListener(v -> openDetailsDailyMealActivity(position));
    }

    @Override
    public int getItemCount() {
        return dailyMealModuleList.size();
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

    private void openDetailsDailyMealActivity(int position) {
        Intent intent = new Intent(context, DetailsDailyMealActivity.class);
        intent.putExtra(CONSTANTS.TYPE,dailyMealModuleList.get(position).getType());
        context.startActivity(intent);
    }
}
