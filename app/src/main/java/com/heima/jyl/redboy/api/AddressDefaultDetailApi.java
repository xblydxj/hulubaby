package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.AddressDefaultDetailBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @创建者 种金币
 * @创建时间 2016/6/18 14:41
 * @描述 获取默认地址的接口
 */
public interface AddressDefaultDetailApi {
    /**
     *
     * @param userid       用户ID
     * @param token        用户请求码
     *
     */
    @FormUrlEncoded
    @POST("addressDefaultDetail")
    Call<AddressDefaultDetailBean> getAddress(@Field("userid") String userid,
                                              @Field("token") String token);
}
