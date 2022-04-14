package com.hamza.ieeechallenge.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hamza.ieeechallenge.Adapters.LastOrderAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.roomDatabase.entities.Cart;

import java.util.List;

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