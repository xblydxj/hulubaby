package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.CarBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @创建者 种金币
 * @创建时间 2016/6/16 19:43
 * @描述 ${TODO}
 */
public interface CartEditApi {
    /**
     * 修改购物车中商品数量
     * @param userid    用户ID
     * @param token     用户信息
     * @param cid       购物车ID
     * @param pnum      商品数量
     * @param pid       商品ID
     * @return
     */
    @FormUrlEncoded
    @POST("cart/edit")
    Call<CarBean> getCart(@Field("userid") String userid,
                          @Field("token") String token,
                          @Field("cid") String cid,
                          @Field("pnum") String pnum,
                          @Field("pid") String pid);


}
