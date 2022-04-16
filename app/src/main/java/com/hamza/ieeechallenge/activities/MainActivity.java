package com.hamza.ieeechallenge.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.ActivityNavigationBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.User;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView name , phone;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigation.toolbar);
        disableNightMode();
        setDrawer();
        setNavigation();
        setUserInfoOnDrawer();
        Button logOutButton = navigationView.findViewById(R.id.btn_logOut);
        logOutButton.setOnClickListener(view -> {
            showConfirmationDialog();
        });

    }

    private void disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }


    private void setDrawer() {
        DrawerLayout drawer = binding.drawerLayout;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_daily_meal, R.id.nav_favourite,R.id.nav_mycart)
                .setOpenableLayout(drawer)
                .build();
    }

    private void setNavigation() {
                navigationView = binding.navView;
                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
                NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
                NavigationUI.setupWithNavController(navigationView, navController);

    }

    private void checkAuthState() {

    }
    private void setUserInfoOnDrawer() {
        findUserNameAndPhoneInNavigationView(navigationView);
        getUserInfoFromFirebase();
    }

    private void findUserNameAndPhoneInNavigationView(NavigationView navigationView) {
        name = navigationView.getHeaderView(0).findViewById(R.id.nameUser);
        phone = navigationView.getHeaderView(0).findViewById(R.id.phoneUser);
    }

    private void getUserInfoFromFirebase() {
        FirebaseFirestore.getInstance().collection(CONSTANTS.COLLECTION_USER).whereEqualTo("id", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .get().addOnCompleteListener(task -> {
            for (DocumentSnapshot snapshot : task.getResult().getDocuments()){
                User user = new User(
                        snapshot.getString(CONSTANTS.NAME),
                        snapshot.getString(CONSTANTS.PHONE)
                );
                name.setText(user.getFirstName());
                phone.setText(user.getPhone());

            }
        });
    }

    private void showConfirmationDialog(){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Log out ?")
                .setMessage("Do you really want to log out ?")
                .setPositiveButton("Yes", (dialogInterface, i) -> logoutUser())
                .setNegativeButton("No" , null)
                .show();
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}