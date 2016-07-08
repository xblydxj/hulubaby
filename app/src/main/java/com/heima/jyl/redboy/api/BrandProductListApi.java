package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.BrandProductListBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface BrandProductListApi {
    @GET("brand/plist")
    Call<BrandProductListBean> getBrandProduct(@Query("page") int page,
                                               @Query("pageNum") int pageNum,
                                               @Query("id") int id,
                                               @Query("orderby") String orderby);
}
