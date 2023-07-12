package com.mpcz.grainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ActivityNewsDetailBinding;

public class NewsDetailActivity extends AppCompatActivity {

    public static String TAG = NewsDetailActivity.class.getCanonicalName();
    ActivityNewsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Glide.with(NewsDetailActivity.this).load(getIntent().getStringExtra(Constants.IMAGE)).into(binding.image);
        Glide.with(NewsDetailActivity.this).load(Constants.IMAGE_URL).into(binding.image);
        binding.txtdetail.setText(getIntent().getStringExtra(Constants.DESCRIPTION));
        binding.txtTitle.setText(getIntent().getStringExtra(Constants.TITLE));

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}