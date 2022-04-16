package com.hamza.ieeechallenge.ui.verifyotp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.activities.MainActivity;
import com.hamza.ieeechallenge.databinding.FragmentVerifyOtpBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class VerifyOtpFragment extends Fragment {

    FirebaseUser firebaseUser;
    private FragmentVerifyOtpBinding binding;
    private String verificationId;
    private String fullNumber;
    private String firstName;
    private String secondName;
    private FirebaseAuth firebaseAuth;
    private String phoneNumber;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVerifyOtpBinding.inflate(getLayoutInflater(), container, false);

        editTextInput();
        getDataFromIntent();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        binding.tvPhoneNumber.setText(phoneNumber);

        binding.btnVerify.setOnClickListener(view12 -> {
            String code = getTextFromEditTexts();
            verifyBeforeSignIn(code);
        });

        return binding.getRoot();
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
        assert getArguments() != null;
        phoneNumber = Objects.requireNonNull(getArguments()).getString(CONSTANTS.PHONENUMBER);
        fullNumber = getArguments().getString(CONSTANTS.FULLNUMBER);
        firstName = getArguments().getString(CONSTANTS.FIRSTNAME);
        secondName = getArguments().getString(CONSTANTS.SECONDNAME);
        verificationId = getArguments().getString(CONSTANTS.VERIFYID);
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
            Toast.makeText(getContext(), "Please enter your OTP", Toast.LENGTH_LONG).show();
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
                openMainActivity();
            }else{
                hideProgressBar();
                Toast.makeText(getContext(), "OTP is not valid", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(getContext() , MainActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
        binding.btnVerify.setVisibility(View.VISIBLE);
    }

    private void InsertUserInDatabase(User user) {
        FirebaseFirestore.getInstance().collection(CONSTANTS.COLLECTION_USER).document(user.getId())
                .set(user).addOnSuccessListener(aVoid -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_verifyOtpFragment_to_nav_home)).addOnFailureListener(e -> Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show());


    }
}