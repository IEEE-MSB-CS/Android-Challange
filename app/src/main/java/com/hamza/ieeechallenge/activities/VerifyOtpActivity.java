package com.hamza.ieeechallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.databinding.ActivityVerifyOtpBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.User;

import java.util.Objects;

public class VerifyOtpActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    private ActivityVerifyOtpBinding binding;
    private String verificationId;
    private String fullNumber;
    private String firstName;
    private String secondName;
    private FirebaseAuth firebaseAuth;
    private String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextInput();
        getDataFromIntent();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        binding.tvPhoneNumber.setText(phoneNumber);

        binding.btnVerify.setOnClickListener(view12 -> {
            String code = getTextFromEditTexts();
            verifyBeforeSignIn(code);
        });

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

    private void getDataFromIntent() {
        phoneNumber = Objects.requireNonNull(getIntent()).getStringExtra(CONSTANTS.PHONENUMBER);
        fullNumber = getIntent().getStringExtra(CONSTANTS.FULLNUMBER);
        firstName = getIntent().getStringExtra(CONSTANTS.FIRSTNAME);
        secondName = getIntent().getStringExtra(CONSTANTS.SECONDNAME);
        verificationId = getIntent().getStringExtra(CONSTANTS.VERIFYID);
    }

    private void verifyBeforeSignIn(String code) {
        if(verificationId != null && code != null) {
            showProgressBar();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnVerify.setVisibility(View.GONE);

    }

    private String getTextFromEditTexts() {
        if(verifyInputIsEmpty()){
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

    private boolean verifyInputIsEmpty() {
        return binding.etC1.getText().toString().trim().isEmpty() ||
                binding.etC1.getText().toString().trim().isEmpty() ||
                binding.etC2.getText().toString().trim().isEmpty() ||
                binding.etC3.getText().toString().trim().isEmpty() ||
                binding.etC4.getText().toString().trim().isEmpty() ||
                binding.etC5.getText().toString().trim().isEmpty() ||
                binding.etC6.getText().toString().trim().isEmpty();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                hideProgressBar();
                FirebaseUser firebaseUser = task.getResult().getUser();
                assert firebaseUser != null;
                User user = new User(firebaseUser.getUid(), firstName, secondName, fullNumber);
                InsertUserInDatabase(user);
            }else{
                hideProgressBar();
                Toast.makeText(this, "OTP is not valid", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
        binding.btnVerify.setVisibility(View.VISIBLE);
    }

    private void InsertUserInDatabase(User user) {
        FirebaseFirestore.getInstance().collection(CONSTANTS.COLLECTION_USER).document(user.getId())
                .set(user).addOnSuccessListener(aVoid ->
                startActivity(new Intent(this , MainActivity.class)))
                .addOnFailureListener(e -> Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show());


    }
}