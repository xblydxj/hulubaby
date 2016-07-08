package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * @创建者 种金币
 * @创建时间 2016/6/18 10:55
 * @描述 提交订单接口的Bean
 */
public class OrdersumbitBean {

    /**
     * addressid : 144
     * carts : [{"id":63,"pid":1,"pnum":5,"ppid":0,"productPrice":"50","state":1,"user_id":"14599337416420"}]
     * couponid : 0
     * deliveryType : 1
     * id : 0
     * invoiceContent : æç»
     * invoiceTitle : 11111
     * invoiceType : 0
     * orderid : 201606181043391
     * paymentType : 0
     * price : 250
     * state : 0
     * time : 1466217819718
     * user_id : 14599337416420
     */

    private OrderInfoBean orderInfo;
    /**
     * orderInfo : {"addressid":"144","carts":[{"id":63,"pid":1,"pnum":5,"ppid":0,"productPrice":"50","state":1,"user_id":"14599337416420"}],"couponid":"0","deliveryType":1,"id":0,"invoiceContent":"æ\u0098\u008eç»\u0086","invoiceTitle":"11111","invoiceType":0,"orderid":"201606181043391","paymentType":0,"price":250,"state":0,"time":1466217819718,"user_id":"14599337416420"}
     * response : orderSumbit
     */

    private String        response;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class OrderInfoBean {
        private String          addressid;
        private String          couponid;
        private String          deliveryType;
        private String          id;
        private String          invoiceContent;
        private String          invoiceTitle;
        private String          invoiceType;
        private String          orderid;
        private String          paymentType;
        private String          price;
        private String          state;
        private long            time;
        private String          user_id;
        /**
         * id : 63
         * pid : 1
         * pnum : 5
         * ppid : 0
         * productPrice : 50
         * state : 1
         * user_id : 14599337416420
         */

        private List<CartsBean> carts;

        public String getAddressid() {
            return addressid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public String getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<CartsBean> getCarts() {
            return carts;
        }

        public void setCarts(List<CartsBean> carts) {
            this.carts = carts;
        }

        public static class CartsBean {
            private String id;
            private String pid;
            private String pnum;
            private String ppid;
            private String productPrice;
            private String state;
            private String user_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPnum() {
                return pnum;
            }

            public void setPnum(String pnum) {
                this.pnum = pnum;
            }

            public String getPpid() {
                return ppid;
            }

            public void setPpid(String ppid) {
                this.ppid = ppid;
            }

            public String getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(String productPrice) {
                this.productPrice = productPrice;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
