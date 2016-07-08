package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.BrandProductListBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface ProductListApi {
    @GET("productlist")
    Call<BrandProductListBean.ProductListBean> getProductList(@Query("page") int page,
                                                              @Query("pageNum") int pageNum,
                                                              @Query("cId") int cId,
                                                              @Query("orderby") String orderby);
}
