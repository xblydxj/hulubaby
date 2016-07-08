package com.heima.jyl.redboy.api;


import com.heima.jyl.redboy.bean.CarBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @创建者 种金币
 * @创建时间 2016/6/15 16:41
 * @描述 进入购物车的接口
 */
public interface CartApi {
    /**
     * 获取购物车信息
     *
     * @param userid   用户ID
     * @param token     用户Token
     * @return          返回购物车信息
     */
    @FormUrlEncoded
    @POST("cart")
    Call<CarBean> getCart(@Field("userid") String userid,
                          @Field("token") String token);
}

