package com.heima.jyl.redboy.utils;


import com.heima.jyl.redboy.api.BaseUrl;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by 46321 on 2016/6/15/015.
 */
public class RetrofitUtil {

    private static Retrofit mRetrofit;
    public static Retrofit RetrofitConfig(){
        return mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /*public static Retrofit RetrofitInit(){
        return mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.basUrl)
                .build();
    }*/
}
