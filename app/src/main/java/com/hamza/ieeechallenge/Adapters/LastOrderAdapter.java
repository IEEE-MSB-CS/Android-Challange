package com.hamza.ieeechallenge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.model.OrderList;

import java.util.ArrayList;
import java.util.List;

public class LastOrderAdapter extends RecyclerView.Adapter<LastOrderAdapter.ViewHolder> {
    List<Food> orderLists = new ArrayList<>();
    Context context;

    public LastOrderAdapter(List<Food> orderLists, Context context) {
        this.orderLists = orderLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LastOrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lastorder_item,parent,false));    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,price,status,restName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lastOrder_img);
            name = itemView.findViewById(R.id.lastorder_name);
            price = itemView.findViewById(R.id.lastorder_price);
            status = itemView.findViewById(R.id.status);
            restName=itemView.findViewById(R.id.resturantname);
        }
    }

}
