package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.AddressDefaultDetailBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @创建者 种金币
 * @创建时间 2016/6/19 16:07
 * @描述 获取地址接口
 */
public interface AddressDetailApi {
    /**
     *
     * @param addressid 地址ID
     * @param token     请求码
     * @param userid    用户ID
     * @return
     */
    @GET("addressDetail")
    Call<AddressDefaultDetailBean> getAddressDetail(@Query("addressid") String addressid,
                                                       @Query("token") String token,
                                                       @Query("userid") String userid);
}
