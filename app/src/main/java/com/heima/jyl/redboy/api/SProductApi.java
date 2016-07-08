package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.SProductBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface SProductApi {
    @GET("product")
    Call<SProductBean> getSProduct(@Query("pId") String pId);
}
