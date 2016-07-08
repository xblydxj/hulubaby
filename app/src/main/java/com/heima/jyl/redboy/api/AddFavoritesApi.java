package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.AddFavouritesBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface AddFavoritesApi {
    @GET("addfavorites")
    Call<AddFavouritesBean> getFavorites(@Query("pid") String pid,
                                         @Query("token") String token,
                                         @Query("userid") String userid);
}
