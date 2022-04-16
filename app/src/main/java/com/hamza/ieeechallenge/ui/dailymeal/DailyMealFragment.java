package com.hamza.ieeechallenge.ui.dailymeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hamza.ieeechallenge.Adapters.DailyMealAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.FragmentDailyMealBinding;
import com.hamza.ieeechallenge.model.DailyMeal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DailyMealFragment extends Fragment {
    private FragmentDailyMealBinding binding;
    List<DailyMeal> dailyMealList;
    DailyMealAdapter dailyMealAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDailyMealBinding.inflate(getLayoutInflater() , container , false);
        fillDailyMealList();
        setDailyMealAdapter();

        return binding.getRoot();
    }

    private void fillDailyMealList() {
        dailyMealList = new ArrayList<>();
        dailyMealList.add(new DailyMeal(R.drawable.breakfast,"Breakfast","from 7 AM to 11 AM","Breakfast"));
        dailyMealList.add(new DailyMeal(R.drawable.dinner,"Dinner","from 4 PM to 7 PM","Dinner"));
        dailyMealList.add(new DailyMeal(R.drawable.launch,"Lunch","from 9 Pm to 12 AM","Lunch"));
        dailyMealList.add(new DailyMeal(R.drawable.sweets,"Sweets","Available all day","Sweets"));
        dailyMealList.add(new DailyMeal(R.drawable.coffee,"Coffee","Available all day","Coffee"));

    }

    private void setDailyMealAdapter() {
        binding.dailyMealRc.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealAdapter = new DailyMealAdapter(dailyMealList,binding.getRoot());
        binding.dailyMealRc.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();
    }

}
