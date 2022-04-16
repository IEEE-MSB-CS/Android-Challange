package com.hamza.ieeechallenge.ui.Favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hamza.ieeechallenge.Adapters.FavouriteAdapter;
import com.hamza.ieeechallenge.databinding.FragmentFavouritBinding;
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite;

import java.util.List;

public class FavouriteFragment extends Fragment {

    private FragmentFavouritBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavouritBinding.inflate(getLayoutInflater() ,container, false );

        FavouriteViewModel favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        favouriteViewModel.readAllData.observe(getViewLifecycleOwner(), favourites -> {
            controlIllustrationAndTextsInEmptyScreen(favourites);
            setFavouritesAdapter(favourites , favouriteViewModel);
        });

        return binding.getRoot();
    }

    private void setFavouritesAdapter(List<Favourite> favourites, FavouriteViewModel favouriteViewModel) {
        FavouriteAdapter adapter = new FavouriteAdapter(favourites , getContext() , favouriteViewModel , binding.getRoot());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity() , 2));
    }

    private void controlIllustrationAndTextsInEmptyScreen(List<Favourite> favourites) {
        if(favourites.size()== 0){
            showIllustrations();
        }else{
            hideIllustrations();
        }
    }

    private void showIllustrations() {
        binding.illustration.setVisibility(View.VISIBLE);
        binding.tvAddItems.setVisibility(View.VISIBLE);
        binding.tvNothingToSee.setVisibility(View.VISIBLE);
    }

    private void hideIllustrations() {
        binding.illustration.setVisibility(View.GONE);
        binding.tvAddItems.setVisibility(View.GONE);
        binding.tvNothingToSee.setVisibility(View.GONE);
    }
}