package com.mpcz.grainmaster.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpcz.grainmaster.Models.UpdatesModel;
import com.mpcz.grainmaster.databinding.ItemUpdatesBinding;

import java.util.ArrayList;

public class UpdatesAdapter extends RecyclerView.Adapter<UpdatesAdapter.ViewHolder> {

    private ArrayList<UpdatesModel.Data> arraylist;
    private Context context;

    public UpdatesAdapter(ArrayList<UpdatesModel.Data> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public UpdatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UpdatesAdapter.ViewHolder(ItemUpdatesBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UpdatesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtSms.setText(arraylist.get(position).getSms_data());
        holder.binding.txtCreatedat.setText(arraylist.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUpdatesBinding binding;

        public ViewHolder(ItemUpdatesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}