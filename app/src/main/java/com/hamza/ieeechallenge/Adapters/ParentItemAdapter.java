package com.hamza.ieeechallenge.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.ieeechallenge.R;
import com.hamza.ieeechallenge.model.ParentRvModel;

import java.security.AccessController;
import java.util.List;

public class ParentItemAdapter  extends RecyclerView.Adapter<ParentItemAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentRvModel>parentRvModelList;

    public ParentItemAdapter(List<ParentRvModel> parentRvModelList) {
        this.parentRvModelList = parentRvModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parentrv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParentRvModel parentRvModel = parentRvModelList.get(position);
        holder.parentTitle.setText(parentRvModel.getTitle());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                        holder
                        .ChildRecyclerView
                        .getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        layoutManager.setInitialPrefetchItemCount(
                parentRvModel.getFoodList().size()
        );
        LastOrderAdapter lastOrderAdapter =new LastOrderAdapter(parentRvModel.getFoodList());
        holder.ChildRecyclerView.setLayoutManager(layoutManager);
        holder.ChildRecyclerView.setAdapter(lastOrderAdapter);
        holder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return parentRvModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView parentTitle;
        private RecyclerView ChildRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentTitle = itemView.findViewById(R.id.parent_title);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }
    }
}
