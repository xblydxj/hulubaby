package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.OrderBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public interface OrderApi {
    @FormUrlEncoded
    @POST("orderlist")
    Call<OrderBean> getList(@Field("type") int type,
                            @Field("token") String token,
                            @Field("userid") String userid,
                            @Field("pageNum") int pageNum,
                            @Field("pageSize") int pageSize);
}
