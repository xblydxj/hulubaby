package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.SearchBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface SearchApi {
    @FormUrlEncoded
      @POST("search")
    Call<SearchBean> getSearch(
            @Field("page") int page,
            @Field("pageNum") int pageNum,
            @Field("orderby") String orderby,
            @Field("keyword") String keyword);

//    @GET("search")
//    Call<SearchBean> getSearch(
//                @Query("page") int page,
//                @Query("pageNum") int pageNum,
//                @Query("orderby") String orderby,
//                @Query("keyword") String keyword);
}
