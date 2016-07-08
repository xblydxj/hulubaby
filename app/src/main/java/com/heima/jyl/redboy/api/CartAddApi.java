package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.BaseBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface CartAddApi {
    @FormUrlEncoded
    @POST("/market/cart/add")
    Call<BaseBean> getCart(@Field("userid") String userid,
                           @Field("pid") String pId,
                           @Field("pnum") int pnum,
                           @Field("ppid") int ppid,
                           @Field("token") String token);
}
