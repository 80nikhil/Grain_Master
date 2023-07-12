package com.mpcz.grainmaster.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mpcz.grainmaster.Models.NewsModel;
import com.mpcz.grainmaster.Models.PlansModel;
import com.mpcz.grainmaster.NewsDetailActivity;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ItemNewsBinding;
import com.mpcz.grainmaster.databinding.ItemPlanBinding;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private ArrayList<PlansModel.PlansModel2> arraylist;
    private Context context;

    public PlanAdapter(ArrayList<PlansModel.PlansModel2> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanAdapter.ViewHolder(ItemPlanBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtSubscription.setText(arraylist.get(position).getName());
        holder.binding.txtDuration.setText(arraylist.get(position).getDuration());
        holder.binding.txtAmount.setText(arraylist.get(position).getPrice());
        holder.binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPlanBinding binding;

        public ViewHolder(ItemPlanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}