package com.mpcz.grainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ActivityMainBinding;
import com.mpcz.grainmaster.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        try {
            binding.edtName.setText(SharedPref.getStr(ProfileActivity.this, Constants.NAME));
            binding.edtCountry.setText(SharedPref.getStr(ProfileActivity.this, Constants.COUNTRY));
            binding.edtCity.setText(SharedPref.getStr(ProfileActivity.this, Constants.CITY));
            binding.edtEmail.setText(SharedPref.getStr(ProfileActivity.this, Constants.EMAIL));
            binding.edtDealsIn.setText(SharedPref.getStr(ProfileActivity.this, Constants.DEALSIN));
            binding.edtMobile.setText(SharedPref.getStr(ProfileActivity.this, Constants.MOBILE_NO));
            binding.edtBussinessName.setText(SharedPref.getStr(ProfileActivity.this, Constants.BUSSINESS_NAME));
            binding.edtBussinessType.setText(SharedPref.getStr(ProfileActivity.this, Constants.BUSSINESS_TYPE));
            binding.edtGstin.setText(SharedPref.getStr(ProfileActivity.this,Constants.GSTIN));
            binding.edtState.setText(SharedPref.getStr(ProfileActivity.this, Constants.STATE));
            Glide.with(ProfileActivity.this).load(Constants.IMAGE_URL+SharedPref.getStr(ProfileActivity.this,Constants.USER_PIC)).error(getResources().getDrawable(R.drawable.profile)).into(binding.imgprofile);
            Glide.with(ProfileActivity.this).load(Constants.IMAGE_URL+SharedPref.getStr(ProfileActivity.this,Constants.BUSSINESS_CARD_PIC)).error(getResources().getDrawable(R.drawable.profile)).into(binding.imgBusinessCard);
        }catch (Exception e){
            e.printStackTrace();
        }

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}