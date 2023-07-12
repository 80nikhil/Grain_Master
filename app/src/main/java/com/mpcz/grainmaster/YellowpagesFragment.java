package com.mpcz.grainmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mpcz.grainmaster.Adapter.YellowPagesAdapter;
import com.mpcz.grainmaster.Adapter.PlanAdapter;
import com.mpcz.grainmaster.Models.YellowPagesModel;
import com.mpcz.grainmaster.databinding.FragmentYellowpagesBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YellowpagesFragment extends Fragment {
    public static final String TAG = YellowpagesFragment.class.getCanonicalName();

    FragmentYellowpagesBinding binding;
    BottomNavigationView bottomNavigationView;

    ArrayList<YellowPagesModel.List> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentYellowpagesBinding.inflate(inflater, container, false);

        getlist();
        return binding.getRoot();
    }

    private void getlist() {
        RetrofitClient.getAPIService().getyellopages().enqueue(new Callback<YellowPagesModel>() {
            @Override
            public void onResponse(Call<YellowPagesModel> call, Response<YellowPagesModel> response) {
                if (response.code() == 200) {
                    arrayList.clear();
                    arrayList = response.body().getList();
                    if (arrayList.size() > 0) {
                        binding.recyclerTask.setVisibility(View.VISIBLE);
                        binding.noDataFound.setVisibility(View.GONE);
                        YellowPagesAdapter yellowPagesAdapter = new YellowPagesAdapter(arrayList, getActivity());
                        binding.recyclerTask.setAdapter(yellowPagesAdapter);
                        yellowPagesAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<YellowPagesModel> call, Throwable t) {
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
        bottomNavigationView.getMenu().findItem(R.id.yellow_pages).setChecked(true);
//        binding.shimmerViewContainer.startShimmerAnimation();
    }
}