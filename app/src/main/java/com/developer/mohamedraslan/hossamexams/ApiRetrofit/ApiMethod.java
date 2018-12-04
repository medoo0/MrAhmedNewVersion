package com.developer.mohamedraslan.hossamexams.ApiRetrofit;

import com.developer.mohamedraslan.hossamexams.JsonModel.All_Country_Details;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiMethod {


    @GET("list-time-zone")
    Call<All_Country_Details> getTiming(@QueryMap Map<String, String> map);

}
