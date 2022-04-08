package com.hamza.ieeechallenge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hamza.ieeechallenge.Adapters.LastOrderAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.model.OrderList;

import java.util.List;

public class LastOrderActivity extends AppCompatActivity {
    RecyclerView rv;
    LastOrderAdapter lastOrderAdapter;
    List<Food> orderLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_order);
        rv = findViewById(R.id.rv_lastorder);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);


        FirebaseFirestore.getInstance().collection(CONSTANTS.ORDER)
                .whereArrayContains("userId",FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot snapshot : task.getResult().getDocuments()){
                    Food food = new Food(
                            snapshot.getString("title"),
                            snapshot.getString("restaurantName"),
                            snapshot.getString("price"),
                            snapshot.getString("image"),
                            snapshot.getString("status")
                    );
                    orderLists.add(food);
                }
                lastOrderAdapter = new LastOrderAdapter(orderLists,LastOrderActivity.this);
                rv.setAdapter(lastOrderAdapter);
            }
        });


    }
}