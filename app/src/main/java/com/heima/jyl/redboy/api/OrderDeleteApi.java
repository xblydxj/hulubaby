package com.heima.jyl.redboy.api;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface OrderDeleteApi {
    @FormUrlEncoded
    @POST("orderdelete")
    Call<ResponseBody> deleteOrder(@Field("orderid") String orderid, @Field("userid") String userid, @Field("token") String token);
}
