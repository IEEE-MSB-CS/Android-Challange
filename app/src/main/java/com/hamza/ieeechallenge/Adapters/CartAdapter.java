package com.hamza.ieeechallenge.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.CartModule;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<CartModule>cartModuleList;

    public CartAdapter(List<CartModule> cartModuleList) {
        this.cartModuleList = cartModuleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(cartModuleList.get(position).getImage());
        holder.name.setText(cartModuleList.get(position).getName());
        holder.price.setText(cartModuleList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return cartModuleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.cart_image);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.price_Cart);
        }
    }
}
