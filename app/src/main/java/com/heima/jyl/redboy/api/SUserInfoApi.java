package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.SUserInfoBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public interface SUserInfoApi {

    @GET("userinfo")
    Call<SUserInfoBean> getUserInfo(@Query("token") String token,
                                    @Query("userid") String userid);
//    @FormUrlEncoded
//    @POST("userinfo")
//    Call<SUserInfoBean> getUserInfo(@Field("token") String token,
//                                    @Field("userid") String userid);
}
