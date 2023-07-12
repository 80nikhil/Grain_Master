package com.mpcz.grainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.mpcz.grainmaster.Models.YellowPagesModel;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ActivityYellowPagesDetailBinding;

public class YellowPagesDetailActivity extends AppCompatActivity {

    public static final String TAG = YellowPagesDetailActivity.class.getCanonicalName();
    ActivityYellowPagesDetailBinding binding;
    YellowPagesModel.List yellowPagesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYellowPagesDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        yellowPagesModel = (YellowPagesModel.List) getIntent().getExtras().getSerializable(Constants.DATA);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            binding.txtAddress.setText(yellowPagesModel.getAddress());
            binding.txtCity.setText(yellowPagesModel.getCity());
            binding.txtBussinessName.setText(yellowPagesModel.getBusiness_name());
            binding.txtBussinessType.setText(yellowPagesModel.getBusiness_type());
            binding.txtCommodity.setText(yellowPagesModel.getDealsin());
            binding.txtGst.setText(yellowPagesModel.getGstin());
            binding.txtName.setText(yellowPagesModel.getName());
            binding.txtMobileno.setText(yellowPagesModel.getMobileno());
            binding.txtState.setText(yellowPagesModel.getState());
            binding.txtCountry.setText(yellowPagesModel.getCountry());
            Glide.with(YellowPagesDetailActivity.this).load(Constants.IMAGE_URL+yellowPagesModel.getUser_pic()).into(binding.userpic);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Glide.with(YellowPagesDetailActivity.this).load(Constants.IMAGE_URL+yellowPagesModel.getBussiness_card_pic()).into(binding.bussinessCardPic);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}