package com.hamza.ieeechallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.hamza.ieeechallenge.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSignOut.setOnClickListener(view1 -> {
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this , SendOtpActivity.class));
        });
    }
}