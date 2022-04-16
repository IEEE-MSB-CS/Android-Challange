package com.hamza.ieeechallenge.ui.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.Adapters.CartAdapter;
import com.hamza.ieeechallenge.databinding.FragmentMyCartBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.roomDatabase.entities.Cart;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MyCartFragment extends Fragment {

    private FragmentMyCartBinding binding;
    private CartAdapter cartAdapter;
    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyCartBinding.inflate(inflater , container , false);
        View view =  binding.getRoot();

        setCartAdapter();

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.readAllData.observe(getViewLifecycleOwner(), carts -> {

            controlIllustrationAndTextsInEmptyScreen(carts);
            cartAdapter.setData(carts , cartViewModel);
            binding.pricetotal.setText(String.valueOf(getTotalPrice(carts)));
            binding.btnOrder.setOnClickListener(v -> showConfirmationDialog());

        });

        return view;
    }

    private void setCartAdapter() {
        cartAdapter = new CartAdapter(getContext());
        binding.cartRv.setAdapter(cartAdapter);
        binding.cartRv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));

    }

    private void showConfirmationDialog(){
        new AlertDialog.Builder(getContext())
                .setTitle("Make order ?")
                .setMessage("Do you really want to make order ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendOrder(cartViewModel);
                    }
                })
                .setNegativeButton("No" , null)
                .show();
    }
    private void sendOrder(CartViewModel cartViewModel) {
        FirebaseFirestore.getInstance().collection(CONSTANTS.ORDER)
                .add(cartViewModel.getReadAllData()).addOnCompleteListener(task -> {});
        Toast.makeText(getContext(), "Order sent successfully !", Toast.LENGTH_LONG).show();
        deleteAllCartItems();
    }

    private void deleteAllCartItems() {
        List<Cart> cartList = cartAdapter.getCartList();
        for(int i=0;i<cartList.size();i++){
            cartViewModel.deleteCartItem(cartList.get(i));
        }
    }

    private void controlIllustrationAndTextsInEmptyScreen(List<Cart> carts) {
        if(carts.size()== 0){
            showIllustrations();
        }else{
            hideIllustrations();
        }
    }

    private void showIllustrations() {
        binding.illustration.setVisibility(View.VISIBLE);
        binding.tvAddItems.setVisibility(View.VISIBLE);
        binding.tvNothingToSee.setVisibility(View.VISIBLE);
    }

    private void hideIllustrations() {
        binding.illustration.setVisibility(View.GONE);
        binding.tvAddItems.setVisibility(View.GONE);
        binding.tvNothingToSee.setVisibility(View.GONE);
    }


    public double getTotalPrice(List<Cart> cartList){
        double totalPrice = 0;
        for (int i=0;i<cartList.size();i++){
            totalPrice = totalPrice + (cartList.get(i).getPrice()*cartList.get(i).getQuantity());
        }
        return totalPrice;
    }


}