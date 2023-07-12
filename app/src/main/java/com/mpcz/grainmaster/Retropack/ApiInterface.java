package com.mpcz.grainmaster.Retropack;

import com.mpcz.grainmaster.Models.BannerModel;
import com.mpcz.grainmaster.Models.CommodityModel;
import com.mpcz.grainmaster.Models.LanguageModel;
import com.mpcz.grainmaster.Models.NewsModel;
import com.mpcz.grainmaster.Models.NotificationModel;
import com.mpcz.grainmaster.Models.PlansModel;
import com.mpcz.grainmaster.Models.ProfileModel;
import com.mpcz.grainmaster.Models.UpdatesModel;
import com.mpcz.grainmaster.Models.YellowPagesModel;
import com.mpcz.grainmaster.Retroresponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST(Constants.LOGIN)
    Call<Retroresponse> loginuser(@Body RequestBody requestBody);

    @POST(Constants.USER_REGISTRATION)
    Call<Retroresponse> register_user(@Body RequestBody requestBody);

    @GET(Constants.BANNER)
    Call<BannerModel> getbanner_image();

    @GET(Constants.ALL_NEWS)
    Call<NewsModel> getall_news();

    @GET(Constants.NOTIFICATION)
    Call<NotificationModel> getall_notification();

    @GET(Constants.GET_LANGUAGE)
    Call<LanguageModel> get_languages();

    @GET(Constants.COMMODITY)
    Call<CommodityModel> get_commodity();

    @GET(Constants.PLAN_LIST)
    Call<PlansModel> getall_plans();

    @POST(Constants.SMS_LIST)
    Call<UpdatesModel> getallupdates(@Body RequestBody requestBody);

    @POST(Constants.USER_INFO)
    Call<ProfileModel> getprofile(@Body RequestBody requestBody);

    @GET(Constants.YELLOW_PAGES)
    Call<YellowPagesModel> getyellopages();

}
