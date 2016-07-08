package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.AddAddressBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ting说你跳 on 2016/6/17.
 */
public interface AddAddressApi {
    @FormUrlEncoded
    @POST("addresssave")
    Call<AddAddressBean> getAddresssave(@Field("id") int id,
                                        @Field("token") String token,
                                        @Field("userid") String userid,
                                        @Field("name") String name,
                                        @Field("phoneNumber") String phoneNumber,
                                        @Field("city") String city,
                                        @Field("province") String province,
                                        @Field("addressArea") String addressArea,
                                        @Field("addressDetail") String addressDetail,
                                        @Field("zipCode") String zipCode);





    /*Call<AddAddressBean.AddressListBean> getAddresssave(@Field("id") int id,
                                                        @Field("token") String token,
                                                        @Field("userid") String userid,
                                                        @Field("name") String name,
                                                        @Field("phoneNumber") String phoneNumber,
                                                        @Field("city")String city,
                                                        @Field("province")String province,
                                                        @Field("addressArea")String addressArea,
                                                        @Field("addressDetail")String addressDetail,
                                                        @Field("zipCode")String zipCode);*/
}
