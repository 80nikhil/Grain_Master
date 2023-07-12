package com.mpcz.grainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mpcz.grainmaster.Models.ProfileModel;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ActivityLoginBinding;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static String TAG = LoginActivity.class.getCanonicalName();
    ActivityLoginBinding binding;
    ProgressDialog progressDialog;
    Boolean user_exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        progressDialog = new ProgressDialog(LoginActivity.this, ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setTitle(getResources().getString(R.string.app_name));
        progressDialog.setMessage(getResources().getString(R.string.please_wait));

        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, VerifyMobileActivity.class));
            }
        });
        binding.sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getprofiledata();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_exist) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    public void getprofiledata() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
//        builder.addFormDataPart(Constants.MOBILENO, SharedPref.getStr(LoginActivity.this, Constants.MOBILE_NO));
        builder.addFormDataPart(Constants.MOBILENO, binding.edtMobile.getText().toString());
        MultipartBody requestBody = builder.build();
        RetrofitClient.getAPIService().getprofile(requestBody).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == true) {
                        SharedPref.setStr(LoginActivity.this, Constants.CID, response.body().getList().getCid());
                        SharedPref.setStr(LoginActivity.this, Constants.NAME, response.body().getList().getName());
                        SharedPref.setStr(LoginActivity.this, Constants.MOBILE_NO, response.body().getList().getMobileno());
                        SharedPref.setStr(LoginActivity.this, Constants.EMAIL, response.body().getList().getEmail());
                        SharedPref.setStr(LoginActivity.this, Constants.ADDRESS, response.body().getList().getAddress());
                        SharedPref.setStr(LoginActivity.this, Constants.COUNTRY, response.body().getList().getCountry());
                        SharedPref.setStr(LoginActivity.this, Constants.STATE, response.body().getList().getState());
                        SharedPref.setStr(LoginActivity.this, Constants.USER_PIC, response.body().getList().getUser_pic());
                        SharedPref.setStr(LoginActivity.this, Constants.CITY, response.body().getList().getCity());
                        SharedPref.setStr(LoginActivity.this, Constants.BUSSINESS_CARD_PIC, response.body().getList().getBussiness_card_pic());
                        SharedPref.setStr(LoginActivity.this, Constants.BUSSINESS_NAME, response.body().getList().getBusiness_name());
                        SharedPref.setStr(LoginActivity.this, Constants.BUSSINESS_TYPE, response.body().getList().getBusiness_type());
                        SharedPref.setStr(LoginActivity.this, Constants.GSTIN, response.body().getList().getGstin());
                        SharedPref.setStr(LoginActivity.this, Constants.DEALS_IN, response.body().getList().getDealsin());
                        SharedPref.setStr(LoginActivity.this, Constants.WALLET, response.body().getList().getWallet());
                        SharedPref.setStr(LoginActivity.this, Constants.ACTIVE_PACK, response.body().getList().getActive_pack());
                        SharedPref.setStr(LoginActivity.this, Constants.PACK_START_DATE, response.body().getList().getPack_start_date());
                        SharedPref.setStr(LoginActivity.this, Constants.PACK_END_DATE, response.body().getList().getPack_end_date());
                        SharedPref.setStr(LoginActivity.this, Constants.LANGUAGE, response.body().getList().getLanguage());
                        SharedPref.setStr(LoginActivity.this, Constants.STATUS, response.body().getList().getStatus());
                        binding.layoutMobileNo.setVisibility(View.GONE);
                        binding.layouyVerifyOtp.setVisibility(View.VISIBLE);
                        user_exist = true;
                    } else {
                        user_exist = false;
                        binding.layoutMobileNo.setVisibility(View.GONE);
                        binding.layouyVerifyOtp.setVisibility(View.VISIBLE);
                    }
                } else {
                    user_exist = false;
                    binding.layoutMobileNo.setVisibility(View.GONE);
                    binding.layouyVerifyOtp.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Server Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}