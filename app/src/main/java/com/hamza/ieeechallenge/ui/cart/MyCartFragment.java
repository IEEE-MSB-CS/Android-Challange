package com.hamza.ieeechallenge.ui.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.Adapters.CartAdapter;
import com.hamza.ieeechallenge.databinding.FragmentMyCartBinding;
import com.hamza.ieeechallenge.model.CartModule;
import com.hamza.ieeechallenge.roomDatabase.Cart;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MyCartFragment extends Fragment {
    private FragmentMyCartBinding binding;
    private ArrayList<Cart> cartList;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyCartBinding.inflate(inflater , container , false);
        View view =  binding.getRoot();

        CartAdapter cartAdapter = new CartAdapter(getContext());
        binding.cartRv.setAdapter(cartAdapter);
        binding.cartRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));

        CartViewModel cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.readAllData.observe(getViewLifecycleOwner(), carts -> {
                cartAdapter.setData(carts);
        });

        return view;
    }


}