package com.mpcz.grainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mpcz.grainmaster.databinding.ActivitySplashBinding;
import com.mpcz.grainmaster.databinding.ActivityVerifyMobileBinding;

public class VerifyMobileActivity extends AppCompatActivity {

    ActivityVerifyMobileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyMobileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutMobile.setVisibility(View.GONE);
                binding.layoutOtp.setVisibility(View.VISIBLE);
                binding.sendOtp.setVisibility(View.GONE);
                binding.verifyOtp.setVisibility(View.VISIBLE);
            }
        });
        binding.verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyMobileActivity.this,RegisterActivity.class));
            }
        });
        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyMobileActivity.this,LoginActivity.class));
            }
        });
    }
}