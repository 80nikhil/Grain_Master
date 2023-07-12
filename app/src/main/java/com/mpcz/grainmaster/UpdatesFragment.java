package com.mpcz.grainmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mpcz.grainmaster.Adapter.PlanAdapter;
import com.mpcz.grainmaster.Adapter.UpdatesAdapter;
import com.mpcz.grainmaster.Models.PlansModel;
import com.mpcz.grainmaster.Models.UpdatesModel;
import com.mpcz.grainmaster.Retropack.Constants;
import com.mpcz.grainmaster.databinding.FragmentPlanBinding;
import com.mpcz.grainmaster.databinding.FragmentUpdatesBinding;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatesFragment extends Fragment {

    public static final String TAG = UpdatesFragment.class.getCanonicalName();
    FragmentUpdatesBinding binding;
    ArrayList<UpdatesModel.Data> arrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdatesBinding.inflate(inflater, container, false);

        getlist();
        return binding.getRoot();
    }

    private void getlist() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(Constants.LANGUAGE, "hindi");
        MultipartBody requestBody = builder.build();
        RetrofitClient.getAPIService().getallupdates(requestBody).enqueue(new Callback<UpdatesModel>() {
            @Override
            public void onResponse(Call<UpdatesModel> call, Response<UpdatesModel> response) {
                if (response.code() == 200) {
                    arrayList.clear();
                    arrayList = response.body().getData();
                    if (arrayList.size() > 0) {
                        binding.recyclerTask.setVisibility(View.VISIBLE);
                        binding.noDataFound.setVisibility(View.GONE);
                        UpdatesAdapter updatesAdapter = new UpdatesAdapter(arrayList, getActivity());
                        binding.recyclerTask.setAdapter(updatesAdapter);
                        updatesAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<UpdatesModel> call, Throwable t) {
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
        bottomNavigationView.getMenu().findItem(R.id.updates).setChecked(true);
        binding.shimmerViewContainer.startShimmerAnimation();
    }
}