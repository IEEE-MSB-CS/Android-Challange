package com.hamza.ieeechallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.databinding.ActivityVerifyOtpBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.User;

public class VerifyOtpActivity extends AppCompatActivity {
    private ActivityVerifyOtpBinding binding;
    private String verificationId,fullNumber,phoneNumber,fname,sname;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        editTextInput();

         phoneNumber = getIntent().getStringExtra(CONSTANTS.PHONENUMBER);
         fullNumber = getIntent().getStringExtra(CONSTANTS.FULLNUMBER);
         fname = getIntent().getStringExtra(CONSTANTS.FIRSTNAME);
         sname = getIntent().getStringExtra(CONSTANTS.SECONDNAME);
        verificationId = getIntent().getStringExtra(CONSTANTS.VERIFYID);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
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
                FirebaseUser firebaseUser = task.getResult().getUser();
                User user = new User(firebaseUser.getUid(), fname, sname, fullNumber);
                putInDB(user);
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
    private void putInDB(User user) {

        FirebaseFirestore.getInstance().collection(CONSTANTS.COLLECTION_USER).document(user.getId())
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
               Intent go = new Intent(VerifyOtpActivity.this, MainActivity.class);
               startActivity(go);
               finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VerifyOtpActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}