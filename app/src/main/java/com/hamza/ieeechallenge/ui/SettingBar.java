package com.hamza.ieeechallenge.ui;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.hamza.ieeechallenge.databinding.ChatScreenBinding;

public class SettingBar {
    Context context;
    ChatScreenBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public SettingBar(Context context, ChatScreenBinding binding) {
        this.context = context;
        this.binding = binding;
        binding.searchBt.setOnClickListener (this::showHidenBar);
        binding.chatRecyclerView.setOnScrollChangeListener (this::onScrollChange);
    }

    public void showHidenBar(View view) {
        binding.hideBar.setVisibility (View.VISIBLE);
        binding.appBarUnhide.setVisibility (View.GONE);
    }

    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (binding.appBarUnhide.getVisibility ( ) == View.GONE)
            cancelShowHidenBar ( );
    }

    private void cancelShowHidenBar() {
        binding.hideBar.setVisibility (View.GONE);
        binding.appBarUnhide.setVisibility (View.VISIBLE);
    }

}

