package com.hamza.ieeechallenge.ui.sendotp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.FragmentSendOtpBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SendOtpFragment extends Fragment {

    private FragmentSendOtpBinding binding;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSendOtpBinding.inflate(getLayoutInflater(), container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        binding.countryPicker.registerCarrierNumberEditText(binding.etPhone);

        binding.btnSendOtp.setOnClickListener(view1 -> {
            if(verifyInputIsValid()){
                sendOtp("+" + binding.countryPicker.getFullNumber().replace(" ",""));
            }
        });

        return binding.getRoot();
    }

    private boolean verifyInputIsValid(){
        if (binding.etPhone.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Please enter your phone number !", Toast.LENGTH_LONG).show();
            return false;
        } else if (binding.etPhone.getText().toString().trim().length() <10) {
            Toast.makeText(getContext(), "Please enter a valid phone number !", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.PHONENUMBER, binding.etPhone.getText().toString());
        bundle.putString(CONSTANTS.FULLNUMBER, "+" + binding.countryPicker.getFullNumber().replace(" ", ""));
        bundle.putString(CONSTANTS.FIRSTNAME, binding.etFirstName.getText().toString());
        bundle.putString(CONSTANTS.SECONDNAME, binding.etSecondName.getText().toString());
        bundle.putString(CONSTANTS.VERIFYID, verificationId);

        Navigation.findNavController(binding.getRoot()).navigate( R.id.action_sendOtpFragment_to_verifyOtpFragment, bundle);
    }


    private void setPhoneOptions(String phoneNumber, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(Objects.requireNonNull(getActivity()))
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}