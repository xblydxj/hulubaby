package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.LimitBuyBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface LimitBuyApi {
    @FormUrlEncoded
    @POST("limitbuy")
    Call<LimitBuyBean> getLimitBuy(@Field("page") int page,
                                   @Field("pageNum") int pageNum);

}
