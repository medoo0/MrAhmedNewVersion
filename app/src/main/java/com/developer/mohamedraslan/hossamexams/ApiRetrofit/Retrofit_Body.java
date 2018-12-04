package com.developer.mohamedraslan.hossamexams.ApiRetrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Body {

    public static Retrofit retrofit = null;
    public static String URLCITY  = "http://api.timezonedb.com/v2.1/";


    public static Retrofit getRetrofit(){


        Gson gson =  new GsonBuilder()
                .create();

        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(URLCITY)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }


}
