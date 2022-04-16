package com.hamza.ieeechallenge.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.ActivitySendOtpBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SendOtpActivity extends AppCompatActivity {

    private ActivitySendOtpBinding binding;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySendOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        binding.countryPicker.registerCarrierNumberEditText(binding.etPhone);

        binding.btnSendOtp.setOnClickListener(view1 -> {
            if(verifyInputIsValid()){
                sendOtp("+" + binding.countryPicker.getFullNumber().replace(" ",""));
            }
        });
    }


    private boolean verifyInputIsValid(){
        if (binding.etPhone.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter your phone number !", Toast.LENGTH_LONG).show();
            return false;
        } else if (binding.etPhone.getText().toString().trim().length() <10) {
            Toast.makeText(this, "Please enter a valid phone number !", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
    void sendOtp(String phoneNumber) {
        showProgressBar();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                hideProgressBar();
            }
            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                Toast.makeText(SendOtpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                hideProgressBar();
            }
            @Override
            public void onCodeSent(@NonNull @NotNull String verificationId, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                hideProgressBar();
                navigateToVerifyOtpFragment(verificationId);
            }
        };
        setPhoneOptions(phoneNumber , mCallbacks);
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnSendOtp.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
        binding.btnSendOtp.setVisibility(View.VISIBLE);
    }

    private void navigateToVerifyOtpFragment(String verificationId) {
        Intent intent = new Intent(this , VerifyOtpActivity.class);
        intent.putExtra(CONSTANTS.PHONENUMBER, binding.etPhone.getText().toString());
        intent.putExtra(CONSTANTS.FULLNUMBER, "+" + binding.countryPicker.getFullNumber().replace(" ", ""));
        intent.putExtra(CONSTANTS.FIRSTNAME, binding.etFirstName.getText().toString());
        intent.putExtra(CONSTANTS.SECONDNAME, binding.etSecondName.getText().toString());
        intent.putExtra(CONSTANTS.VERIFYID, verificationId);
        startActivity(intent);
    }


    private void setPhoneOptions(String phoneNumber, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}