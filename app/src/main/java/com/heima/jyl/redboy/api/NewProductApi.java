package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.NewProductBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface NewProductApi {
    @GET("newproduct")
    Call<NewProductBean> getNewProduct(@Query("page") int page,
                                       @Query("pageNum") int pageNum,
                                       @Query("orderby") String orderby);
}
