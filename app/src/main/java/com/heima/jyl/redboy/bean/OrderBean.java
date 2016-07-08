package com.heima.jyl.redboy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/17.
 */
public class OrderBean extends BaseBean {


    /**
     * orderList : [{"addressid":"144","carts":[{"id":52,"orderid":"201604141212053","pid":4,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/4.jpg","productName":"玩具小鸭子c","productPrice":"168","state":2,"user_id":"14599337416420"}],"couponid":"1","deliveryType":1,"id":36,"invoiceContent":"办公用品","invoiceTitle":"悟空","invoiceType":0,"orderid":"201604141212053","paymentType":0,"price":168,"state":2,"time":1460607125843,"user_id":"14599337416420"}]
     * total : 1
     */

    private int                 total;
    /**
     * addressid : 144
     * carts : [{"id":52,"orderid":"201604141212053","pid":4,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/4.jpg","productName":"玩具小鸭子c","productPrice":"168","state":2,"user_id":"14599337416420"}]
     * couponid : 1
     * deliveryType : 1
     * id : 36
     * invoiceContent : 办公用品
     * invoiceTitle : 悟空
     * invoiceType : 0
     * orderid : 201604141212053
     * paymentType : 0
     * price : 168
     * state : 2
     * time : 1460607125843
     * user_id : 14599337416420
     */

    private List<OrderListBean> orderList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean implements Parcelable {
        private String          addressid;
        private String          couponid;
        private int             deliveryType;
        private int             id;
        private String          invoiceContent;
        private String          invoiceTitle;
        private int             invoiceType;
        private String          orderid;
        private int             paymentType;
        private int             price;
        private int             state;
        private long            time;
        private String          user_id;
        /**
         * id : 52
         * orderid : 201604141212053
         * pid : 4
         * pnum : 1
         * ppid : 0
         * productImageUrl : /images/topic/product/4.jpg
         * productName : 玩具小鸭子c
         * productPrice : 168
         * state : 2
         * user_id : 14599337416420
         */

        private List<CartBean> carts;

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

        public int getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(int deliveryType) {
            this.deliveryType = deliveryType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
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

        public List<CartBean> getCarts() {
            return carts;
        }

        public void setCarts(List<CartBean> carts) {
            this.carts = carts;
        }

        public static class CartBean implements Parcelable {
            private int    id;
            private String orderid;
            private int    pid;
            private int    pnum;
            private int    ppid;
            private String productImageUrl;
            private String productName;
            private String productPrice;
            private int    state;
            private String user_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getPnum() {
                return pnum;
            }

            public void setPnum(int pnum) {
                this.pnum = pnum;
            }

            public int getPpid() {
                return ppid;
            }

            public void setPpid(int ppid) {
                this.ppid = ppid;
            }

            public String getProductImageUrl() {
                return productImageUrl;
            }

            public void setProductImageUrl(String productImageUrl) {
                this.productImageUrl = productImageUrl;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(String productPrice) {
                this.productPrice = productPrice;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.orderid);
                dest.writeInt(this.pid);
                dest.writeInt(this.pnum);
                dest.writeInt(this.ppid);
                dest.writeString(this.productImageUrl);
                dest.writeString(this.productName);
                dest.writeString(this.productPrice);
                dest.writeInt(this.state);
                dest.writeString(this.user_id);
            }

            public CartBean() {
            }

            protected CartBean(Parcel in) {
                this.id = in.readInt();
                this.orderid = in.readString();
                this.pid = in.readInt();
                this.pnum = in.readInt();
                this.ppid = in.readInt();
                this.productImageUrl = in.readString();
                this.productName = in.readString();
                this.productPrice = in.readString();
                this.state = in.readInt();
                this.user_id = in.readString();
            }

            public static final Creator<CartBean> CREATOR = new Creator<CartBean>() {
                @Override
                public CartBean createFromParcel(Parcel source) {
                    return new CartBean(source);
                }

                @Override
                public CartBean[] newArray(int size) {
                    return new CartBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.addressid);
            dest.writeString(this.couponid);
            dest.writeInt(this.deliveryType);
            dest.writeInt(this.id);
            dest.writeString(this.invoiceContent);
            dest.writeString(this.invoiceTitle);
            dest.writeInt(this.invoiceType);
            dest.writeString(this.orderid);
            dest.writeInt(this.paymentType);
            dest.writeInt(this.price);
            dest.writeInt(this.state);
            dest.writeLong(this.time);
            dest.writeString(this.user_id);
            dest.writeList(this.carts);
        }

        public OrderListBean() {
        }

        protected OrderListBean(Parcel in) {
            this.addressid = in.readString();
            this.couponid = in.readString();
            this.deliveryType = in.readInt();
            this.id = in.readInt();
            this.invoiceContent = in.readString();
            this.invoiceTitle = in.readString();
            this.invoiceType = in.readInt();
            this.orderid = in.readString();
            this.paymentType = in.readInt();
            this.price = in.readInt();
            this.state = in.readInt();
            this.time = in.readLong();
            this.user_id = in.readString();
            this.carts = new ArrayList<CartBean>();
            in.readList(this.carts, CartBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<OrderListBean> CREATOR = new Parcelable.Creator<OrderListBean>() {
            @Override
            public OrderListBean createFromParcel(Parcel source) {
                return new OrderListBean(source);
            }

            @Override
            public OrderListBean[] newArray(int size) {
                return new OrderListBean[size];
            }
        };
    }
}
