package com.mpcz.grainmaster.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpcz.grainmaster.Models.UpdatesModel;
import com.mpcz.grainmaster.Models.YellowPagesModel;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.YellowPagesDetailActivity;
import com.mpcz.grainmaster.databinding.ItemYellowPagesBinding;

import java.util.ArrayList;

public class YellowPagesAdapter extends RecyclerView.Adapter<YellowPagesAdapter.ViewHolder> {

    private ArrayList<YellowPagesModel.List> arraylist;
    private Context context;

    public YellowPagesAdapter(ArrayList<YellowPagesModel.List> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public YellowPagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YellowPagesAdapter.ViewHolder(ItemYellowPagesBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull YellowPagesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtCity.setText(arraylist.get(position).getCity());
        holder.binding.txtName.setText(arraylist.get(position).getName());
        holder.binding.txtCommodity.setText(arraylist.get(position).getDealsin());
        holder.binding.txtState.setText(arraylist.get(position).getState());
        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, YellowPagesDetailActivity.class).putExtra(Constants.DATA,arraylist.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemYellowPagesBinding binding;

        public ViewHolder(ItemYellowPagesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}