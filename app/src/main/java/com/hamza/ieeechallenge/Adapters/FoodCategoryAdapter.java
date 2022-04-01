package com.hamza.ieeechallenge.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.FoodCategory;
import com.hamza.ieeechallenge.model.Food;

import java.util.ArrayList;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder> {

    UpdateFoodRC updateFoodRC;
    Activity activity;
    ArrayList<FoodCategory> foodCategoryList;
    boolean check = true;
    boolean select = true;
    int row_index = -1;
    ArrayList<Food> pizzaList ;
    ArrayList<Food> ice_creamList ;
    ArrayList<Food> burgerList ;
    ArrayList<Food> saladList ;
    ArrayList<Food> sandwichList;

    public FoodCategoryAdapter(UpdateFoodRC updateFoodRC, Activity activity, ArrayList<FoodCategory> foodCategoryList, ArrayList<Food> pizzaList, ArrayList<Food> ice_creamList, ArrayList<Food> burgerList, ArrayList<Food> saladList, ArrayList<Food> sandwichList) {
        this.updateFoodRC = updateFoodRC;
        this.activity = activity;
        this.foodCategoryList = foodCategoryList;
        this.pizzaList = pizzaList;
        this.ice_creamList = ice_creamList;
        this.burgerList = burgerList;
        this.saladList = saladList;
        this.sandwichList = sandwichList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(foodCategoryList.get(position).getImage());
        holder.name.setText(foodCategoryList.get(position).getName());

        if (check) {
          updateFoodRC.callback(position, saladList);
            check = false;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                if (position == 0) {
                    updateFoodRC.callback(position, saladList);
                } else if (position == 1) {
                    updateFoodRC.callback(position, pizzaList);
                } else if (position == 2) {
                    updateFoodRC.callback(position, burgerList);
                } else if (position == 3) {
                    updateFoodRC.callback(position, ice_creamList);
                } else if (position == 4) {
                    updateFoodRC.callback(position, sandwichList);
                }
            }
        });


        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.chaneg_bg);
                select=false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.chaneg_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.default_bg);

            }
        }
    }



    @Override
    public int getItemCount() {
        return foodCategoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imageView;
        TextView name;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_image);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
