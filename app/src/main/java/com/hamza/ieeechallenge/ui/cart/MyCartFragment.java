package com.hamza.ieeechallenge.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.Adapters.CartAdapter;
import com.hamza.ieeechallenge.databinding.FragmentMyCartBinding;
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
                cartAdapter.setData(carts , cartViewModel);
                binding.pricetotal.setText(String.valueOf(getTotalPrice(carts)));
        });

        return view;
    }
    public double getTotalPrice(List<Cart> cartList){
        double totalPrice = 0;
        for (int i=0;i<cartList.size();i++){
            totalPrice = totalPrice + (cartList.get(i).getPrice()*cartList.get(i).getQuantity());
        }
        return totalPrice;
    }

}