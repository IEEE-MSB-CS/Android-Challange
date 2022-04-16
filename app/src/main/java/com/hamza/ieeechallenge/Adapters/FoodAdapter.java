package com.hamza.ieeechallenge.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite;
import com.hamza.ieeechallenge.ui.Favourite.FavouriteViewModel;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    View view;
    Context context;
    ArrayList<Food> foodList;
    FavouriteViewModel favouriteViewModel;
    LifecycleOwner lifecycleOwner;

    public FoodAdapter(View view, ArrayList<Food> foodList , Context context ) {
        this.view = view;
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(foodList.get(position).getImage())
                .into(holder.image);
        holder.title.setText(foodList.get(position).getTitle());
        holder.restaurantName.setText(foodList.get(position).getRestaurant());
        holder.price.setText(foodList.get(position).getPrice());

       favouriteViewModel.isFavourite(foodList.get(position).getTitle()).observe(lifecycleOwner, (Observer<String>) s ->
               updateFavouriteIconStatus(s , holder));

        holder.image.setOnClickListener(view -> navigateToFoodDetailFragment(position));

        holder.favourite.setOnClickListener(view ->
                updateFavouriteItemIconByFavouriteText(holder , position));
    }

    @Override
    public int getItemCount() {
        return (foodList == null)? 0 : foodList.size();
    }

    public void filterList(ArrayList<Food> filter){
      foodList = filter;
      notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image , favourite;
        TextView title,restaurantName,price , isFavourite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ver_image);
            title = itemView.findViewById(R.id.productName);
            restaurantName = itemView.findViewById(R.id.tv_restaurant_name);
            price = itemView.findViewById(R.id.price);
            favourite = itemView.findViewById(R.id.iv_heart);
            isFavourite = itemView.findViewById(R.id.tv_is_favourite);
        }
    }

    private void updateFavouriteIconStatus(String s, ViewHolder holder) {
        if(s==null || s=="0" ){
           setFavouriteBorderIcon(holder);
        }else{
            setFavouriteFilledIcon(holder);
        }
    }

    private void updateFavouriteItemIconByFavouriteText(ViewHolder holder, int position) {
        if(holder.isFavourite.getText().equals("1")){
            setFavouriteBorderIcon(holder);
            deleteFavouriteItemFromDatabase(position);
        }else{
            setFavouriteFilledIcon(holder);
            addFavouriteItemToDatabase(position);
        }
    }

    private void setFavouriteBorderIcon(ViewHolder holder) {
        holder.favourite.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.ic_baseline_favorite_border_24));
        holder.isFavourite.setText("0");
    }

    private void setFavouriteFilledIcon(ViewHolder holder) {
        holder.favourite.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.ic_baseline_favorite_24));
        holder.isFavourite.setText("1");
    }

    private void navigateToFoodDetailFragment(int position) {
        final Bundle bundle = new Bundle();
        bundle.putString("title",foodList.get(position).getTitle());
        bundle.putString("image",foodList.get(position).getImage());
        bundle.putString("price",foodList.get(position).getPrice());
        bundle.putDouble("rating",foodList.get(position).getRating());
        bundle.putString("restaurantName",foodList.get(position).getRestaurant());
        bundle.putString("description",foodList.get(position).getDescription());

        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_foodlDetailFragment , bundle);

    }

    private void deleteFavouriteItemFromDatabase(int position) {
        Favourite favouriteItem = new Favourite(foodList.get(position).getId() , foodList.get(position).getTitle(),
                foodList.get(position).getRestaurant() ,foodList.get(position).getRating(),
                foodList.get(position).getPrice() , foodList.get(position).getDescription() , foodList.get(position).getImage() ,
                "1");
        favouriteViewModel.deleteFavouriteItem(favouriteItem);
    }

    public void addFavouriteItemToDatabase(int position){
        Favourite favouriteItem = new Favourite(foodList.get(position).getId() , foodList.get(position).getTitle(),
                foodList.get(position).getRestaurant() ,foodList.get(position).getRating(),
                foodList.get(position).getPrice() , foodList.get(position).getDescription() , foodList.get(position).getImage() ,
                "1");
        favouriteViewModel.addToFavourite(favouriteItem);
        Toast.makeText(context.getApplicationContext(), "Item added to Favourite", Toast.LENGTH_SHORT).show();
    }

    public void setData(FavouriteViewModel favouriteViewModel , LifecycleOwner lifecycleOwner){
        this.favouriteViewModel = favouriteViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }
}
