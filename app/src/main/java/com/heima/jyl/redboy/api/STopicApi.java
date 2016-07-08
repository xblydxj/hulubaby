package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.STopicBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public interface STopicApi {
    @GET("topic")
    Call<STopicBean> getSTopic(@Query("page") int page,
                              @Query("pageNum") int pageNum);
}
