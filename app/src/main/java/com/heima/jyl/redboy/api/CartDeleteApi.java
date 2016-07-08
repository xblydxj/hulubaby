package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.CarBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @创建者 种金币
 * @创建时间 2016/6/16 23:12
 * @描述 ${TODO}
 */
public interface CartDeleteApi {
    @FormUrlEncoded
    @POST("cart/delete")
    Call<CarBean> deleteCart(@Field("userid") String userid,
                          @Field("token") String token,
                          @Field("cids") String cids);
}
