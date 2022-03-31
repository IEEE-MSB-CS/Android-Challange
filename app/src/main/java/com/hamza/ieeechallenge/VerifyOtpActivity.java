package com.hamza.ieeechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hamza.ieeechallenge.activities.MainActivity;
import com.hamza.ieeechallenge.databinding.ActivityVerifyOtpBinding;

public class VerifyOtpActivity extends AppCompatActivity {
    private ActivityVerifyOtpBinding binding;
    private String verificationId;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        editTextInput();

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String fullNumber = getIntent().getStringExtra("fullNumber");
        verificationId = getIntent().getStringExtra("verificationId");
        firebaseAuth = FirebaseAuth.getInstance();

        binding.tvPhoneNumber.setText(phoneNumber);

        binding.tvResendOtp.setOnClickListener(view1 -> {
            sendOtp(fullNumber);
        });
        binding.btnVerify.setOnClickListener(view12 -> {
            String code = getTextFromEditTexts();
            if(verificationId != null && code != null) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnVerify.setVisibility(View.GONE);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                signInWithPhoneAuthCredential(credential);
            }
            });

    }

    private void sendOtp(String fullNumber) {

    }


    private void editTextInput() {
        binding.etC1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.etC2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.etC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.etC3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.etC3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.etC4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.etC4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.etC5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.etC5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.etC6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private String getTextFromEditTexts() {
        if(binding.etC1.getText().toString().trim().isEmpty() ||
                binding.etC1.getText().toString().trim().isEmpty() ||
                binding.etC2.getText().toString().trim().isEmpty() ||
                binding.etC3.getText().toString().trim().isEmpty() ||
                binding.etC4.getText().toString().trim().isEmpty() ||
                binding.etC5.getText().toString().trim().isEmpty() ||
                binding.etC6.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter your OTP", Toast.LENGTH_LONG).show();

        }else {
            return binding.etC1.getText().toString() +
                    binding.etC2.getText().toString() +
                    binding.etC3.getText().toString() +
                    binding.etC4.getText().toString() +
                    binding.etC5.getText().toString() +
                    binding.etC6.getText().toString();
        }
        return null;
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                binding.progressBar.setVisibility(View.GONE);
                binding.btnVerify.setVisibility(View.VISIBLE);
                Intent intent = new Intent(VerifyOtpActivity.this , MainActivity.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else{
                binding.progressBar.setVisibility(View.GONE);
                binding.btnVerify.setVisibility(View.VISIBLE);
                Toast.makeText(VerifyOtpActivity.this, "OTP is not valid", Toast.LENGTH_LONG).show();
            }
        });
    }

}