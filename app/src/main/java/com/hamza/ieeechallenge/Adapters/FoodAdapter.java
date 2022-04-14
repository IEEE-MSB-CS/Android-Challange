package com.hamza.ieeechallenge.Adapters;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.activities.FoodDetailActivity;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite;
import com.hamza.ieeechallenge.ui.Favourite.FavouriteViewModel;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    Context context;
    ArrayList<Food> foodList;
    FavouriteViewModel favouriteViewModel;
    LifecycleOwner lifecycleOwner;

    public FoodAdapter(Context context, ArrayList<Food> foodList ) {
        this.context = context;
        this.foodList = foodList;
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

        holder.image.setOnClickListener(view -> openFoodDetailActivity(position));

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

    private void openFoodDetailActivity(int position) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra("title",foodList.get(position).getTitle());
        intent.putExtra("image",foodList.get(position).getImage());
        intent.putExtra("price",foodList.get(position).getPrice());
        intent.putExtra("rating",foodList.get(position).getRating());
        intent.putExtra("restaurantName",foodList.get(position).getRestaurant());
        intent.putExtra("description",foodList.get(position).getDescription());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
