package com.heima.jyl.redboy.api;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public interface AddressDeleteApi {
    @GET("addressdelete")
    Call<ResponseBody> deleteAddress(@Query("id")int id,
                                     @Query("token")String token,
                                     @Query("userid")String userid);
}
