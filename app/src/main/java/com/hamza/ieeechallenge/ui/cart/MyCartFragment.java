package com.hamza.ieeechallenge.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Toast;

import androidx.annotation.NonNull;
=======

>>>>>>> 8810404818562217df4808e31d60af7da2926aa1
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.Adapters.CartAdapter;
import com.hamza.ieeechallenge.databinding.FragmentMyCartBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
=======
import com.hamza.ieeechallenge.Adapters.CartAdapter;
import com.hamza.ieeechallenge.databinding.FragmentMyCartBinding;
>>>>>>> 8810404818562217df4808e31d60af7da2926aa1
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {
    private FragmentMyCartBinding binding;
    private ArrayList<Cart> cartList;
<<<<<<< HEAD
    double totalPrice = 0;
=======
>>>>>>> 8810404818562217df4808e31d60af7da2926aa1
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
<<<<<<< HEAD
                binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseFirestore.getInstance().collection(CONSTANTS.ORDER)
                                .add(cartViewModel.getReadAllData()).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(getContext(), "Order sent ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        });


        return view;
    }
    public double getTotalPrice(List<Cart> cartList){

=======
        });

        return view;
    }
    public double getTotalPrice(List<Cart> cartList){
        double totalPrice = 0;
>>>>>>> 8810404818562217df4808e31d60af7da2926aa1
        for (int i=0;i<cartList.size();i++){
            totalPrice = totalPrice + (cartList.get(i).getPrice()*cartList.get(i).getQuantity());
        }
        return totalPrice;
    }

<<<<<<< HEAD

=======
>>>>>>> 8810404818562217df4808e31d60af7da2926aa1
}