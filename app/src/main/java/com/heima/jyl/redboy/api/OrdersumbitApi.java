package com.heima.jyl.redboy.api;

import com.heima.jyl.redboy.bean.OrdersumbitBean;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * @创建者 种金币
 * @创建时间 2016/6/18 11:00
 * @描述 生成订单的接口
 */
public interface OrdersumbitApi {
    /**
     * @param userid         用户ID
     * @param token          用户请求码
     * @param cids           购物车Id
     * @param addressid      地址簿ID
     * @param paymentType    付方式式  1：在线支付 2：货到付款
     * @param deliveryType   送货时间  1：任何时间 2：仅限工作日 3：仅限休息日
     * @param invoiceType    发票类型  0：不需要发票 1：个人 2：单位
     * @param invoiceTitle   发票抬头
     * @param invoiceContent 发票内容
     */
    @FormUrlEncoded
    @POST("ordersumbit")
    Call<OrdersumbitBean> delOrdersumbit(@Field("userid") String userid,
                                         @Field("token") String token,
                                         @Field("cids") String cids,
                                         @Field("addressid") int addressid,
                                         @Field("paymentType") int paymentType,
                                         @Field("deliveryType") int deliveryType,
                                         @Field("invoiceType") int invoiceType,
                                         @Field("invoiceTitle") String invoiceTitle,
                                         @Field("invoiceContent") String invoiceContent);
}
