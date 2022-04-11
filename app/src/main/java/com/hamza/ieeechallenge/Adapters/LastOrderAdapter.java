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
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart;

import java.util.ArrayList;
import java.util.List;

public class LastOrderAdapter extends RecyclerView.Adapter<LastOrderAdapter.ViewHolder> {
    List<Cart> orderLists = new ArrayList<>();
    Context context;

    public LastOrderAdapter(List<Cart> orderLists) {
        this.orderLists = orderLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LastOrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chiledrv_item,parent,false));    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(orderLists.get(position).getTitle());
        holder.price.setText((int) orderLists.get(position).getPrice());
        holder.restName.setText(orderLists.get(position).getRestaurantName());
        holder.status.setText(orderLists.get(position).getStatus());
        Glide.with(context)
                .load(orderLists.get(position).getImage())
                .into(holder.imageView);

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
