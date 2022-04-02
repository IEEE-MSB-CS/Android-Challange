package com.hamza.ieeechallenge.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.R;

public class ChatCustomAdapter extends RecyclerView.Adapter<ChatHolder> {
    Context context;
public ChatCustomAdapter(Context context ){
    this.context = context;
}
    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.chat_custom_view,parent,false);
        return new ChatHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder chatHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
