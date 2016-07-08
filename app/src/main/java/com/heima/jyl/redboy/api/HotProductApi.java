package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.HotProductBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface HotProductApi {
    @GET("hotproduct")
    Call<HotProductBean> getHotProduct(@Query("page") int page,
                                       @Query("pageNum") int pageNum,
                                       @Query("orderby") String orderby);
}
