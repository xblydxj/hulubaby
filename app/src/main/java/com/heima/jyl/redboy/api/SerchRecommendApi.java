package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.SearchreCommendBean;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface SerchRecommendApi {
    @GET("search/recommend")
    Call<SearchreCommendBean> getSearchreCommendBean();
}
