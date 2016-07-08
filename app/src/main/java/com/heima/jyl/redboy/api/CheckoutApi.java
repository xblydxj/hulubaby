package com.heima.jyl.redboy.api;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @创建者 种金币
 * @创建时间 2016/6/20 11:23
 * @描述 付款接口
 */
public interface CheckoutApi {
    /**
     * @param userid
     * @param orderid
     * @param alipaycount
     * @param alipaypwd
     * @param paymoney
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("checkout")
    Call<ResponseBody> setPay(@Field("userid") String userid,
                              @Field("token") String token,
                              @Field("orderid") String orderid,
                              @Field("alipaycount") String alipaycount,
                              @Field("alipaypwd") String alipaypwd,
                              @Field("paymoney") String paymoney);
}
