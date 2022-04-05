package com.hamza.ieeechallenge.ui;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.hamza.ieeechallenge.databinding.ChatScreenBinding;
import com.hamza.ieeechallenge.ui.adapter.ChatCustomAdapter;

public class SettingsChatRecyclerView {
    ChatScreenBinding binding;
    Context context;

    public SettingsChatRecyclerView(ChatScreenBinding binding, Context context){
        this.binding = binding;
        this.context = context;
        setLayoutManager ();
        setAdapter ();

    }

    private void setLayoutManager(){
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager (context);
        binding.chatRecyclerView.setLayoutManager (layoutManager);
    }

    private void setAdapter(){
        ChatCustomAdapter adapter;
        adapter = new ChatCustomAdapter (context);
        binding.chatRecyclerView.setAdapter (adapter);
    }

}
