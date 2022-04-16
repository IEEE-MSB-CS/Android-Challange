package com.hamza.ieeechallenge.ui.fooddetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hamza.ieeechallenge.databinding.FragmentFoodDetailBinding;
import com.hamza.ieeechallenge.roomDatabase.entities.Cart;
import com.hamza.ieeechallenge.ui.cart.CartViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FoodDetailFragment extends Fragment {

    private FragmentFoodDetailBinding binding;
    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodDetailBinding.inflate(getLayoutInflater(), container, false);

        setDataToFragmentFromBundle();
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        FirebaseUser user = getCurrentUser();

        binding.btnAddToCart.setOnClickListener(view -> insertDataToCartDatabase(user.getUid()));

        binding.btnMinus.setOnClickListener(view -> decreaseQuantity());

        binding.btnPlus.setOnClickListener(view -> increaseQuantity());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideActionBar();
    }

    private void hideActionBar() {
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();

    }

    @Override
    public void onStop() {
        super.onStop();
        showActionBar();
    }

    private void showActionBar() {
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();

    }

    private void setDataToFragmentFromBundle() {
        assert getArguments() != null;
        binding.tvTitle.setText(getArguments().getString("title"));
        binding.tvRestaurantName.setText(getArguments().getString("restaurantName"));
        binding.tvPrice.setText(getArguments().getString("price"));
        binding.tvRating.setText(String.valueOf(getArguments().getDouble("rating",0)));
        binding.tvDescription.setText(getArguments().getString("description"));
        Glide.with(this)
                .load(getArguments().getString("image"))
                .into(binding.ivFood);
        binding.ratingBar.setRating((float) getArguments().getDouble("rating",0));
    }


    private FirebaseUser getCurrentUser(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser();
    }

    private void insertDataToCartDatabase(String userId) {
        String title = binding.tvTitle.getText().toString();
        String restaurantName = binding.tvRestaurantName.getText().toString();
        double price = Double.parseDouble(binding.tvPrice.getText().toString().replace("$","").replace(" " , ""));
        int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        assert getArguments() != null;
        String image = Objects.requireNonNull(getArguments()).getString("image");

        Cart cart = new Cart(0 , userId,"Progress", title , image , restaurantName  , quantity , price);
        cartViewModel.addToCart(cart);

        Toast.makeText(getContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
    }

    private void decreaseQuantity() {
        int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        if (quantity>1){
            quantity--;
            binding.tvQuantity.setText(String.valueOf(quantity));
        }
    }

    private void increaseQuantity() {
        int quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        quantity++;
        binding.tvQuantity.setText(String.valueOf(quantity));
    }
}