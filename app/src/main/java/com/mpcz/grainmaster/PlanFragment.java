package com.mpcz.grainmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mpcz.grainmaster.Adapter.PlanAdapter;
import com.mpcz.grainmaster.Models.PlansModel;
import com.mpcz.grainmaster.databinding.FragmentPlanBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanFragment extends Fragment {

    public static final String TAG = PlanFragment.class.getCanonicalName();
    FragmentPlanBinding binding;
    ArrayList<PlansModel.PlansModel2> arrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlanBinding.inflate(inflater, container, false);

        getlist();
        return binding.getRoot();
    }

    private void getlist() {
        RetrofitClient.getAPIService().getall_plans().enqueue(new Callback<PlansModel>() {
            @Override
            public void onResponse(Call<PlansModel> call, Response<PlansModel> response) {
                if (response.code() == 200) {
                    arrayList.clear();
                    arrayList = response.body().getData();
                    if (arrayList.size() > 0) {
                        binding.recyclerTask.setVisibility(View.VISIBLE);
                        binding.noDataFound.setVisibility(View.GONE);
                        PlanAdapter planAdapter = new PlanAdapter(arrayList, getActivity());
                        binding.recyclerTask.setAdapter(planAdapter);
                        planAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<PlansModel> call, Throwable t) {
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
        bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottonnav);
        bottomNavigationView.getMenu().findItem(R.id.plans).setChecked(true);
        binding.shimmerViewContainer.startShimmerAnimation();
    }
}