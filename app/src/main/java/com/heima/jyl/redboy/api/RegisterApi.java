package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.RegisterBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RegisterApi  {
    @FormUrlEncoded
    @POST("register")
    Call<RegisterBean> getRegister(@Field("username") String username,
                                   @Field("password") String password);
}
