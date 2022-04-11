package com.hamza.ieeechallenge.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hamza.ieeechallenge.Adapters.FoodCategoryAdapter;
import com.hamza.ieeechallenge.Adapters.FoodAdapter;
import com.hamza.ieeechallenge.Adapters.UpdateFoodRC;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.activities.LastOrderActivity;
import com.hamza.ieeechallenge.databinding.FragmentHomeBinding;
import com.hamza.ieeechallenge.model.FoodCategory;
import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.model.JSONResponse;
import com.hamza.ieeechallenge.ui.Favourite.FavouriteViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements UpdateFoodRC {
    private FragmentHomeBinding binding;

  ArrayList<FoodCategory> foodCategoryList;
  ArrayList<Food> foodList;
  FoodCategoryAdapter foodCategoryAdapter;
  FoodAdapter foodAdapter;
    ArrayList<Food> pizzaList ;
    ArrayList<Food> ice_creamList ;
    ArrayList<Food> burgerList ;
    ArrayList<Food> saladList ;
    ArrayList<Food> sandwichList;
    SearchView searchView;
  //https://run.mocky.io/v3/42febb3a-e4cb-4b7c-9cac-bd98299bbd7c
    FirebaseFirestore firebaseFirestore;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        binding.fab.setOnClickListener(view -> {

        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FoodApiCall foodApiCall = retrofit.create(FoodApiCall.class);
        Call<JSONResponse> call = foodApiCall.getFood();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NotNull Call<JSONResponse> call, @NotNull Response<JSONResponse> response) {
                if(response.code() == 200){
                    JSONResponse jsonResponse;
                    jsonResponse = response.body();
                    assert jsonResponse != null;
                    pizzaList = new ArrayList<>(Arrays.asList(jsonResponse.getPizza()));
                    ice_creamList = new ArrayList<>(Arrays.asList(jsonResponse.getIce_cream()));
                    burgerList = new ArrayList<>(Arrays.asList(jsonResponse.getBurger()));
                    saladList = new ArrayList<>(Arrays.asList(jsonResponse.getSalad()));
                    sandwichList = new ArrayList<>(Arrays.asList(jsonResponse.getSandwich()));

                    //Horizontal recyclerview
                    foodCategoryList = new ArrayList<>();
                    foodCategoryList.add(new FoodCategory(R.drawable.salad, "Salad"));
                    foodCategoryList.add(new FoodCategory(R.drawable.pizza, "Pizza"));
                    foodCategoryList.add(new FoodCategory(R.drawable.hamburger, "Burger"));
                    foodCategoryList.add(new FoodCategory(R.drawable.icecream, "Ice Cream"));
                    foodCategoryList.add(new FoodCategory(R.drawable.sandwich22, "Sandwich"));

                    foodCategoryAdapter =  new FoodCategoryAdapter(HomeFragment.this,getActivity(), foodCategoryList,pizzaList,ice_creamList,burgerList,saladList,sandwichList);
                    binding.homeRecylerview.setAdapter(foodCategoryAdapter);

                    binding.homeRecylerview.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.HORIZONTAL,false));
                    binding.homeRecylerview.setHasFixedSize(true);
                    binding.homeRecylerview.setNestedScrollingEnabled(false);
                }

            }

            @Override
            public void onFailure(@NotNull Call<JSONResponse> call, @NotNull Throwable t) {

            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go =  new Intent(getContext(), LastOrderActivity.class);
                startActivity(go);
            }
        });




        return binding.getRoot();

    }


    @Override
    public void callback(int position, ArrayList<Food> list ) {
        foodAdapter = new FoodAdapter(getContext(), list);
        foodAdapter.notifyDataSetChanged();
        binding.homeRecylerviewVertical.setAdapter(foodAdapter);
        binding.homeRecylerviewVertical.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.VERTICAL,false));

        FavouriteViewModel favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        foodAdapter.setData(favouriteViewModel);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.navigation,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setQueryHint("Enter searched food here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }


    private void filter(String newText) {
        ArrayList<Food> filter = new ArrayList<>();
        for (Food item :pizzaList){
            if (item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filter.add(item);
            }
        }
        for (Food item :ice_creamList){
            if (item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filter.add(item);
            }
        }
        for (Food item :burgerList){
            if (item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filter.add(item);
            }
        }
        for (Food item :saladList){
            if (item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filter.add(item);
            }
        }
        for (Food item :sandwichList){
            if (item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filter.add(item);
            }
        }

        foodAdapter.filterList(filter);
    }

}