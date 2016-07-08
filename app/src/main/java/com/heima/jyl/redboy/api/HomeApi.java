package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.HomeBean;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface HomeApi {
    @GET("home")
    Call<HomeBean> getHome();
}
