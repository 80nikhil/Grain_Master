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
import com.mpcz.grainmaster.NewsDetailActivity;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ItemNewsBinding;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel.NewsModel2> arraylist;
    private Context context;

    public NewsAdapter(ArrayList<NewsModel.NewsModel2> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.txtTitle.setText(arraylist.get(position).getTitle());
        holder.binding.txtDescription.setText(arraylist.get(position).getDescription().substring(0,25)+".....");
        holder.binding.txtCreatedat.setText(arraylist.get(position).getCreateddate());
//        Glide.with(context).load(arraylist.get(position).getImage()).into(holder.binding.image);
        Glide.with(context).load(Constants.IMAGE_URL).into(holder.binding.image);
        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsDetailActivity.class);
                i.putExtra(Constants.TITLE,arraylist.get(position).getTitle());
                i.putExtra(Constants.IMAGE,arraylist.get(position).getImage());
                i.putExtra(Constants.CREATEDDATE,arraylist.get(position).getCreateddate());
                i.putExtra(Constants.DESCRIPTION,arraylist.get(position).getDescription());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;

        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}