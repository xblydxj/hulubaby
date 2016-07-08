package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.FavoritesBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ting说你跳 on 2016/6/15.
 */
public interface FavoritesApi {
//    @GET("favorites")
//    Call<FavoritesBean> getFavorites(@Query("userid")String userid,
//                                     @Query("token")String token,
//                                     @Query("page") int page,
//                                     @Query("pageNum")int pageNum
//                                     );
    @FormUrlEncoded
    @POST("favorites")
    Call<FavoritesBean> getFavorites(@Field("userid") String userid,
                                       @Field("token") String token,
                                       @Field("page") int page,
                                       @Field("pageNum")int pageNum
    );
}
