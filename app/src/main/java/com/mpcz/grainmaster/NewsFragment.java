package com.mpcz.grainmaster;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mpcz.grainmaster.Adapter.NewsAdapter;
import com.mpcz.grainmaster.Models.BannerModel;
import com.mpcz.grainmaster.Models.NewsModel;
import com.mpcz.grainmaster.databinding.FragmentNewsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    public static String TAG = NewsFragment.class.getCanonicalName();
    FragmentNewsBinding binding;
    ArrayList<BannerModel.BannerModel2> bannerimage_list = new ArrayList<>();
    ArrayList<NewsModel.NewsModel2> arrayList = new ArrayList<>();
    List<SlideModel> slideModels = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);

        getbannerimage();
        getlist();
        return binding.getRoot();
    }

    public void getbannerimage() {
        RetrofitClient.getAPIService().getbanner_image().enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == true) {
                        bannerimage_list.clear();
                        bannerimage_list = response.body().getData();
                        if (bannerimage_list.size() > 0) {
                            for (int i = 0; i < bannerimage_list.size(); i++) {
                                slideModels.add(new SlideModel(bannerimage_list.get(i).getImage_path(), ScaleTypes.FIT));
                            }
                            binding.imageSlider.setImageList(slideModels);
                        }
                    }
                }
                if (response.code() == 400) {
                    Log.e(TAG, "INSIDE FAILED BLOCK OF API");
                }
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Log.e(TAG, "fail");
            }
        });
    }

    private void getlist() {
        RetrofitClient.getAPIService().getall_news().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.code() == 200) {
                    arrayList.clear();
                    arrayList = response.body().getData();
                    if (arrayList.size() > 0) {
                        binding.recyclerTask.setVisibility(View.VISIBLE);
                        binding.noDataFound.setVisibility(View.GONE);
                        NewsAdapter newsAdapter = new NewsAdapter(arrayList, getActivity());
                        binding.recyclerTask.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();
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
            public void onFailure(Call<NewsModel> call, Throwable t) {
                binding.recyclerTask.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                binding.shimmerViewContainer.stopShimmerAnimation();
                binding.shimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottonnav);
        bottomNavigationView.getMenu().findItem(R.id.news).setChecked(true);
        binding.shimmerViewContainer.startShimmerAnimation();
    }
}