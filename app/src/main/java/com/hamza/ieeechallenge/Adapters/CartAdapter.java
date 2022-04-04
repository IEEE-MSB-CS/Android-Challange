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
import com.hamza.ieeechallenge.roomDatabase.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<Cart> cartList;
    Context context;

    public CartAdapter( Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(cartList.get(position).getImage())
                .into(holder.imageView);
        holder.name.setText(cartList.get(position).getTitle());
        holder.price.setText(String.valueOf(cartList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return cartList == null? 0 : cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.cart_image);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.price_Cart);
        }
    }

    public void setData(List<Cart> cartList){
        this.cartList = cartList;
        notifyDataSetChanged();
    }
}
