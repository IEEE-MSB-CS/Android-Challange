package com.hamza.ieeechallenge.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite;
import com.hamza.ieeechallenge.ui.Favourite.FavouriteViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    List<Favourite> favouriteList;
    Context context;
    FavouriteViewModel favouriteViewModel;
    View view;

    public FavouriteAdapter(List<Favourite> favouriteList, Context context, FavouriteViewModel favouriteViewModel , View view) {
        this.favouriteList = favouriteList;
        this.context = context;
        this.favouriteViewModel = favouriteViewModel;
        this.view = view;
    }

    @NonNull
    @NotNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new FavouriteAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavouriteAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(favouriteList.get(position).getImage())
                .into(holder.image);
        holder.title.setText(favouriteList.get(position).getTitle());
        holder.restaurantName.setText(favouriteList.get(position).getRestaurantName());
        holder.price.setText(favouriteList.get(position).getPrice());

        holder.favourite.setOnClickListener(view -> deleteFavouriteItem(position));

        holder.cardView.setOnClickListener(view -> openFoodDetailActivity(position));
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image , favourite;
        TextView title , restaurantName , price ;
        CardView cardView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_food);
            title = itemView.findViewById(R.id.tv_title);
            restaurantName = itemView.findViewById(R.id.tv_restaurant_name);
            price = itemView.findViewById(R.id.tv_price);
            favourite = itemView.findViewById(R.id.iv_favourite);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    void deleteFavouriteItem(int position ){
        Favourite favouriteItem = new Favourite(favouriteList.get(position).getId() , favouriteList.get(position).getTitle(),
                favouriteList.get(position).getRestaurantName() ,favouriteList.get(position).getRating(),
                favouriteList.get(position).getPrice() , favouriteList.get(position).getDescription() , favouriteList.get(position).getImage() , "1");
        favouriteViewModel.deleteFavouriteItem(favouriteItem);
    }

    private void openFoodDetailActivity(int position) {

        final Bundle bundle = new Bundle();
        bundle.putString("title",favouriteList.get(position).getTitle());
        bundle.putString("image",favouriteList.get(position).getImage());
        bundle.putString("price",favouriteList.get(position).getPrice());
        bundle.putDouble("rating",favouriteList.get(position).getRating());
        bundle.putString("restaurantName",favouriteList.get(position).getRestaurantName());
        bundle.putString("description",favouriteList.get(position).getDescription());

        Navigation.findNavController(view).navigate(R.id.action_nav_favourite_to_foodlDetailFragment , bundle);
    }
}
