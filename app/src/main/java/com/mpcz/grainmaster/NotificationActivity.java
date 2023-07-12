package com.mpcz.grainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mpcz.grainmaster.Adapter.NotificationAdapter;
import com.mpcz.grainmaster.Adapter.PlanAdapter;
import com.mpcz.grainmaster.Models.NewsModel;
import com.mpcz.grainmaster.Models.NotificationModel;
import com.mpcz.grainmaster.Models.PlansModel;
import com.mpcz.grainmaster.databinding.ActivityMainBinding;
import com.mpcz.grainmaster.databinding.ActivityNotificationBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    ActivityNotificationBinding binding;
    ArrayList<NotificationModel.Data> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getlist();
    }
    private void getlist() {
        RetrofitClient.getAPIService().getall_notification().enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                if (response.code() == 200) {
                    arrayList.clear();
                    arrayList = response.body().getData();
                    if (arrayList.size() > 0) {
                        binding.recyclerTask.setVisibility(View.VISIBLE);
                        binding.noDataFound.setVisibility(View.GONE);
                        NotificationAdapter notificationAdapter = new NotificationAdapter(arrayList, NotificationActivity.this);
                        binding.recyclerTask.setAdapter(notificationAdapter);
                        notificationAdapter.notifyDataSetChanged();
                        binding.shimmerViewContainer.stopShimmerAnimation();
                        binding.shimmerViewContainer.setVisibility(View.GONE);
                    } else {
                        binding.recyclerTask.setVisibility(View.GONE);
                        binding.noDataFound.setVisibility(View.VISIBLE);
                        binding.shimmerViewContainer.stopShimmerAnimation();
                        binding.shimmerViewContainer.setVisibility(View.GONE);
                    }
                } else {
                    binding.recyclerTask.setVisibility(View.GONE);
                    binding.noDataFound.setVisibility(View.VISIBLE);
                    Log.e(TAG, "Something went Wrong " + response.code());
                    binding.shimmerViewContainer.stopShimmerAnimation();
                    binding.shimmerViewContainer.setVisibility(View.GONE);
                }
                if (response.code() == 400) {
                    binding.recyclerTask.setVisibility(View.GONE);
                    binding.noDataFound.setVisibility(View.VISIBLE);
                    Log.e(TAG, "INSIDE FAILED BLOCK OF API");
                    binding.shimmerViewContainer.stopShimmerAnimation();
                    binding.shimmerViewContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                binding.recyclerTask.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.VISIBLE);
                binding.shimmerViewContainer.stopShimmerAnimation();
                binding.shimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerViewContainer.startShimmerAnimation();
    }
}