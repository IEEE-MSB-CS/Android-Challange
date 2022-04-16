package com.hamza.ieeechallenge.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.activities.MainActivity;
import com.hamza.ieeechallenge.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(this::checkAuthState, 3500);

    }

    private void checkAuthState() {
        FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user == null){
                startActivity(new Intent(this , SendOtpActivity.class));
            }else{
                startActivity(new Intent(this , MainActivity.class));
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
