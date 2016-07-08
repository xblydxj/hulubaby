package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.AddressBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ting说你跳 on 2016/6/17.
 */
public interface AddressManageApi {
    @GET("addresslist")
    Call<AddressBean> getAddressList(@Query("userid")String userid,
                                     @Query("token")String token);

}
