package com.hamza.ieeechallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(this::checkAuthState, 2000);
    }

    private void checkAuthState() {
        FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user == null){
                startActivity(new Intent(SplashActivity.this , SendOtpActivity.class));
            }else{
                startActivity(new Intent(SplashActivity.this , MainActivity.class));
            }
            finish();
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}