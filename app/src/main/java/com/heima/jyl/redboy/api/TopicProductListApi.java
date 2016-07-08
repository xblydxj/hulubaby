package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.TopicProductListBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface TopicProductListApi {
    @GET("topic/plist")
    Call<TopicProductListBean>
    getTopicProductList(@Query("page") int page,
                        @Query("pageNum") int pageNum,
                        @Query("id") int id,
                        @Query("orderby") String orderby);
}
