package com.mpcz.grainmaster.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpcz.grainmaster.Models.NotificationModel;
import com.mpcz.grainmaster.Models.UpdatesModel;
import com.mpcz.grainmaster.databinding.ItemNotificationBinding;
import com.mpcz.grainmaster.databinding.ItemUpdatesBinding;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<NotificationModel.Data> arraylist;
    private Context context;

    public NotificationAdapter(ArrayList<NotificationModel.Data> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationAdapter.ViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtTitle.setText(arraylist.get(position).getTitle());
        holder.binding.txtDescription.setText(arraylist.get(position).getDescription());
        holder.binding.txtCreatedAt.setText(arraylist.get(position).getCreatedate());
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNotificationBinding binding;

        public ViewHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}