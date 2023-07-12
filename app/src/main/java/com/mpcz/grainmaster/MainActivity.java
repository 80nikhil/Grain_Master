package com.mpcz.grainmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mpcz.grainmaster.Models.ProfileModel;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.ActivityMainBinding;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getCanonicalName();
    ActivityMainBinding binding;
    TextView name, mobile_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getprofiledata();
        View headerView = binding.navigationDrawer.getHeaderView(0);
        name = (TextView) headerView.findViewById(R.id.txt_name);
        mobile_no = (TextView) headerView.findViewById(R.id.txt_mobileno);
        name.setText(SharedPref.getStr(MainActivity.this, Constants.NAME));
        mobile_no.setText(SharedPref.getStr(MainActivity.this, Constants.MOBILE_NO));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new NewsFragment());
        transaction.commit();
        binding.drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        binding.navigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_news:
                        loadFragment(new NewsFragment());
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_plans:
                        loadFragment(new PlanFragment());
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_updates:
                        loadFragment(new UpdatesFragment());
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_yellow_pages:
                        loadFragment(new YellowpagesFragment());
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_share:
                        shareapp();
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_support:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_terms:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_select_commodity:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawer_logout:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                }
                return true;
            }
        });
        binding.bottonnav.setSelectedItemId(R.id.news);
        binding.bottonnav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.news:
                loadFragment(new NewsFragment());
                return true;

            case R.id.plans:
                loadFragment(new PlanFragment());
                return true;

            case R.id.yellow_pages:
                loadFragment(new YellowpagesFragment());
                return true;

            case R.id.updates:
                loadFragment(new UpdatesFragment());
                return true;
        }
        return false;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void getprofiledata() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
//        builder.addFormDataPart(Constants.MOBILENO, SharedPref.getStr(MainActivity.this, Constants.MOBILE_NO));
        builder.addFormDataPart(Constants.MOBILENO, "7898944693");
        MultipartBody requestBody = builder.build();
        RetrofitClient.getAPIService().getprofile(requestBody).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == true) {
                        SharedPref.setStr(MainActivity.this, Constants.CID, response.body().getList().getCid());
                        SharedPref.setStr(MainActivity.this, Constants.NAME, response.body().getList().getName());
                        SharedPref.setStr(MainActivity.this, Constants.MOBILE_NO, response.body().getList().getMobileno());
                        SharedPref.setStr(MainActivity.this, Constants.EMAIL, response.body().getList().getEmail());
                        SharedPref.setStr(MainActivity.this, Constants.ADDRESS, response.body().getList().getAddress());
                        SharedPref.setStr(MainActivity.this, Constants.COUNTRY, response.body().getList().getCountry());
                        SharedPref.setStr(MainActivity.this, Constants.STATE, response.body().getList().getState());
                        SharedPref.setStr(MainActivity.this, Constants.USER_PIC, response.body().getList().getUser_pic());
                        SharedPref.setStr(MainActivity.this, Constants.CITY, response.body().getList().getCity());
                        SharedPref.setStr(MainActivity.this, Constants.BUSSINESS_CARD_PIC, response.body().getList().getBussiness_card_pic());
                        SharedPref.setStr(MainActivity.this, Constants.BUSSINESS_NAME, response.body().getList().getBusiness_name());
                        SharedPref.setStr(MainActivity.this, Constants.BUSSINESS_TYPE, response.body().getList().getBusiness_type());
                        SharedPref.setStr(MainActivity.this, Constants.GSTIN, response.body().getList().getGstin());
                        SharedPref.setStr(MainActivity.this, Constants.DEALS_IN, response.body().getList().getDealsin());
                        SharedPref.setStr(MainActivity.this, Constants.WALLET, response.body().getList().getWallet());
                        SharedPref.setStr(MainActivity.this, Constants.ACTIVE_PACK, response.body().getList().getActive_pack());
                        SharedPref.setStr(MainActivity.this, Constants.PACK_START_DATE, response.body().getList().getPack_start_date());
                        SharedPref.setStr(MainActivity.this, Constants.PACK_END_DATE, response.body().getList().getPack_end_date());
                        SharedPref.setStr(MainActivity.this, Constants.LANGUAGE, response.body().getList().getLanguage());
                        SharedPref.setStr(MainActivity.this, Constants.STATUS, response.body().getList().getStatus());
                        name.setText(SharedPref.getStr(MainActivity.this, Constants.NAME));
                        mobile_no.setText(SharedPref.getStr(MainActivity.this, Constants.MOBILE_NO));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.e(TAG, "fail");
            }
        });
    }
    public void shareapp(){
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String body = "Your body here";
        String sub = "Your Subject";
        myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
        myIntent.putExtra(Intent.EXTRA_TEXT,body);
        startActivity(Intent.createChooser(myIntent, "Share Using"));
    }
}