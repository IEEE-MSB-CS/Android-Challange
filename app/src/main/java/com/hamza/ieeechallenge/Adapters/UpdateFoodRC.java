package com.hamza.ieeechallenge.Adapters;

import com.hamza.ieeechallenge.model.Food;
import com.hamza.ieeechallenge.roomDatabase.Favourite;
import com.hamza.ieeechallenge.ui.Favourite.FavouriteViewModel;

import java.util.ArrayList;
import java.util.List;

public interface UpdateFoodRC {
    void callback(int position, ArrayList<Food> list);
}
