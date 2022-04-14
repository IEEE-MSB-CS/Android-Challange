package com.hamza.ieeechallenge.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.hamza.ieeechallenge.Adapters.DetailedDailyMealAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.ActivityDetailsDailyMealBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.DailyMealDetialed;
import com.hamza.ieeechallenge.roomDatabase.entities.Cart;
import com.hamza.ieeechallenge.ui.cart.CartViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailsDailyMealActivity extends AppCompatActivity {

    private ActivityDetailsDailyMealBinding binding;
    List<DailyMealDetialed> dailyMealDetailedList;
    DetailedDailyMealAdapter dailyMealAdapter;
    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsDailyMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String dailyMealType = getIntent().getStringExtra(CONSTANTS.TYPE);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        setDetailedRecyclerView();
        setDataToRecyclerView(dailyMealType);

        binding.fabAddToCart.setOnClickListener(view ->
                addMealsToCart());
    }


    private void setDetailedRecyclerView() {
        binding.detaildeRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dailyMealDetailedList = new ArrayList<>();
        dailyMealAdapter = new DetailedDailyMealAdapter(dailyMealDetailedList , this);
        binding.detaildeRv.setAdapter(dailyMealAdapter);
    }

    private void setDataToRecyclerView(String DailyMealType) {

        if (DailyMealType != null && DailyMealType.equalsIgnoreCase("Breakfast"))
            addBreakfastObjectsToDailyMealDetailedList();

        if (DailyMealType != null && DailyMealType.equalsIgnoreCase("Sweets"))
            addSweetsObjectsToDailyMealDetailedList();

        if (DailyMealType != null && DailyMealType.equalsIgnoreCase("Dinner"))
            addDinnerObjectsToDailyMealDetailedList();

        if (DailyMealType != null && DailyMealType.equalsIgnoreCase("Lunch"))
            addLunchObjectsToDailyMealDetailedList();

        if (DailyMealType != null && DailyMealType.equalsIgnoreCase("Coffee"))
            addCoffeeObjectsToDailyMealDetailedList();

    }


    private void addBreakfastObjectsToDailyMealDetailedList() {
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1600326145552-327f74b9c189?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80",
                "Pancake","Pane Vino","20"));
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1588580261949-f17eacb905c9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",
                "Omelette" , "Pane Vino","25"));
    }

    private void addSweetsObjectsToDailyMealDetailedList() {
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1582293041079-7814c2f12063?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",
                "Donuts","Les Orientalistes","10"));
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1602351447937-745cb720612f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=386&q=80",
                "Chocolate Cake" , "Les Orientalistes","30"));
    }

    private void addDinnerObjectsToDailyMealDetailedList() {
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1595295333158-4742f28fbd85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80",
                "Spaghetti","MIM","25"));
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1626082910497-b2987c968923?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=929&q=80",
                "Fried Chicken" , "MIM","50"));
    }

    private void addLunchObjectsToDailyMealDetailedList() {
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1600477064324-92a31919ffd2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
                "Fruit Salad","Nairu","30"));
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1521390188846-e2a3a97453a0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
                "Sandwich Turkey" , "Nairu","25"));
    }

    private void addCoffeeObjectsToDailyMealDetailedList() {
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80",
                "Coffee","Rustica","10"));
        dailyMealDetailedList.add(new DailyMealDetialed("https://images.unsplash.com/photo-1541167760496-1628856ab772?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1037&q=80",
                "Coffee Latte" , "Rustica","25"));
    }

    private void addMealsToCart() {
        List<DailyMealDetialed> dailyMealDetailedList = dailyMealAdapter.getDailyMealList();
        for(int i=0;i<dailyMealDetailedList.size();i++)
            InsertMealToCartDatabase(dailyMealDetailedList.get(i));

        Toast.makeText(this , "Meals added to cart" , Toast.LENGTH_LONG).show();

    }

    private void InsertMealToCartDatabase(DailyMealDetialed dailyMealDetialed) {
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        Cart cart = new Cart(0 , userId , "Progress" , dailyMealDetialed.getName() , dailyMealDetialed.getImage() ,
                dailyMealDetialed.getRestaurant() , 1 , Double.parseDouble(dailyMealDetialed.getPrice()));
        cartViewModel.addToCart(cart);
    }
}