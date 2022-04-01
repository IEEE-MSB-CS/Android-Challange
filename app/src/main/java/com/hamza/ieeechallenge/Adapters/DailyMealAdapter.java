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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageDaily.setImageResource(dailyMealModuleList.get(position).getImage());
        holder.name.setText(dailyMealModuleList.get(position).getName());
        holder.descrip.setText(dailyMealModuleList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsDailyMealActivity.class);
                intent.putExtra(CONSTANTS.TYPE,dailyMealModuleList.get(position).getType());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dailyMealModuleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageDaily;
        TextView name, descrip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDaily = itemView.findViewById(R.id.image_item_daily_meal);
            name = itemView.findViewById(R.id.name_meal_daily);
            descrip = itemView.findViewById(R.id.description);
        }
    }
}
