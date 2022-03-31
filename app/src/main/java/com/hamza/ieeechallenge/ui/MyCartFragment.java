package com.hamza.ieeechallenge.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hamza.ieeechallenge.Adapters.CartAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.ROOM.myCartRoom;
import com.hamza.ieeechallenge.activities.MainActivity;
import com.hamza.ieeechallenge.model.CartModule;
import com.hamza.ieeechallenge.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {
    List<CartModule>list;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    private ImageView primage;
    private TextView price,name;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_rv);
        recyclerView.setHasFixedSize(true);
        primage  = view.findViewById(R.id.cart_image);
        price = view.findViewById(R.id.price_Cart);
        name = view.findViewById(R.id.cart_name);

        Intent intent= getActivity().getIntent();
        final String imageurl=intent.getStringExtra("imageurl");
        final String prname=intent.getStringExtra("prname");
        final String prprice=intent.getStringExtra("prprice");
        final int id=intent.getIntExtra("id",0);
        Glide.with(this).load(imageurl).into(primage);
        price.setText(prprice);
        CartModule cartModule = new CartModule();
        cartModule.setImage(imageurl);
        cartModule.setId(id);
        cartModule.setName(prname);
        cartModule.setPrice(prprice);


        return view;
    }


}