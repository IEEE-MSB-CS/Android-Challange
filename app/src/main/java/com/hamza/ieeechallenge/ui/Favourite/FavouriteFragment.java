package com.hamza.ieeechallenge.ui.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hamza.ieeechallenge.Adapters.FavouriteAdapter;
import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.databinding.FavouriteItemBinding;
import com.hamza.ieeechallenge.databinding.FragmentFavouritBinding;
import com.hamza.ieeechallenge.roomDatabase.Favourite;

import java.util.List;

public class FavouriteFragment extends Fragment {
    private FragmentFavouritBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        
        binding = FragmentFavouritBinding.inflate(getLayoutInflater() ,container, false );

        FavouriteViewModel favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        favouriteViewModel.readAllData.observe(getViewLifecycleOwner(), favourites -> {
            FavouriteAdapter adapter = new FavouriteAdapter(favourites , getContext() , favouriteViewModel);
            binding.recyclerView.setAdapter(adapter);
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 2));
        });

        return binding.getRoot();
    }


}