package com.hamza.ieeechallenge.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.hamza.ieeechallenge.Adapters.DetailedDailyMeal;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.ActivityDetailsDailyMealBinding;
import com.hamza.ieeechallenge.model.CONSTANTS;
import com.hamza.ieeechallenge.model.DailyMealDetialed;

import java.util.ArrayList;
import java.util.List;

public class DetailsDailyMealActivity extends AppCompatActivity {
    private ActivityDetailsDailyMealBinding binding;
    RecyclerView rv;
    List<DailyMealDetialed> dailyMealDetialedList;
    DetailedDailyMeal dailyMealAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsDailyMealBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        String type= getIntent().getStringExtra(CONSTANTS.TYPE);
        binding.detaildeRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dailyMealDetialedList = new ArrayList<>();
        dailyMealDetialedList.add(new DailyMealDetialed());
        dailyMealAdapter = new DetailedDailyMeal(dailyMealDetialedList);
        binding.detaildeRv.setAdapter(dailyMealAdapter);

        if (type != null && type.equalsIgnoreCase("Breakfast")){
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.pancake,"Pancake","Pancake with Honey","20 $"));
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.omlitewithveg,"Omelette" , "Omelette with Vegitabels","25 $"));

        }
        if (type != null && type.equalsIgnoreCase("Sweets")){
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.sweets,"Donutes","Dountes","10 $"));
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.chocolatecake,"Chocolate Cake" , "Chocolate Cake","30 $"));

        }
        if (type != null && type.equalsIgnoreCase("Dinner")){
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.dinner,"Spaghetti","Spaghetti","25 $"));
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.freidchicken,"Fried Chicken" , "Fried Chicken 4 pieces","50 $"));

        }if (type != null && type.equalsIgnoreCase("Lunch")){
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.fruitsalad,"Fruit Salad","Fruit Salad","30 $"));
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.sandwichc,"Sandwich Turkey" , "Sandwich Turkey ","25 $"));

        }
        if (type != null && type.equalsIgnoreCase("Coffe")){
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.coffee,"Coffee","Black Coffee","10 $"));
            dailyMealDetialedList.add(new DailyMealDetialed(R.drawable.latte,"Coffee Latte" , "Coffee Latte ","25 $"));

        }




    }
}