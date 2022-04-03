package com.hamza.ieeechallenge.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.R;

public class ChatHolder extends RecyclerView.ViewHolder{
        TextView contacName,date,lastMessage;

        public ChatHolder(@NonNull View itemView) {
            super (itemView);
            contacName = itemView.findViewById (R.id.contacName);
            date = itemView.findViewById (R.id.date);
            lastMessage = itemView.findViewById (R.id.lastMessage);
        }

    }

