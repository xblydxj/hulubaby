package com.heima.jyl.redboy.api;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public interface OrderCancelApi {
    @FormUrlEncoded
    @POST("ordercancel")
    Call<ResponseBody> cancelOrder(@Field("orderid") String orderid, @Field("userid") String userid, @Field("token") String token);
}
