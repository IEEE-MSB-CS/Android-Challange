package com.hamza.ieeechallenge.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hamza.ieeechallenge.Adapters.LastOrderAdapter;
import com.hamza.ieeechallenge.Adapters.ParentItemAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.model.OrderList;
import com.hamza.ieeechallenge.model.ParentRvModel;
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LastOrderActivity extends AppCompatActivity {
    RecyclerView rv;
    LastOrderAdapter lastOrderAdapter;
    List<Cart> orderLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_order);
        rv = findViewById(R.id.rv_lastorder);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);


     FirebaseFirestore.getInstance().collectionGroup(CONSTANTS.ORDER)
            .whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid())
             .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
         @Override
         public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
             if (queryDocumentSnapshots.isEmpty()) {
                 Log.d("msg", "onSuccess: LIST EMPTY");
                 return;
             } else {

                 List<Cart> types = queryDocumentSnapshots.toObjects(Cart.class);

                 // Add all to your list
                 orderLists.addAll(types);
                 Log.d("msg", "onSuccess: " + orderLists);
             }
             lastOrderAdapter = new LastOrderAdapter(orderLists);
             rv.setAdapter(lastOrderAdapter);
         }
     });


    }

}