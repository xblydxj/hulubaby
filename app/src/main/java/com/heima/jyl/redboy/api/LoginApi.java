package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.LoginBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface LoginApi {
    @FormUrlEncoded
    @POST("login")
    Call<LoginBean> getLogin(@Field("username") String username,
                             @Field("password") String password);
}
