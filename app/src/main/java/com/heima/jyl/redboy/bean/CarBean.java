package com.heima.jyl.redboy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @创建者 种金币
 * @创建时间 2016/6/15 16:39
 * @描述 进入购物车接口的Bean
 */
public class CarBean extends BaseBean{


    /**
     * cart : [{"id":29,"orderid":"00000000000000000001","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":30,"orderid":"00000000000000000001","pid":2,"pnum":4,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":31,"orderid":"00000000000000000001","pid":3,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/3.jpg","productName":"男士孕妇服","productPrice":"300","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":32,"orderid":"00000000000000000001","pid":6,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/6.jpg","productName":"玩具积木a","productPrice":"52","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":33,"orderid":"00000000000000000001","pid":4,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/4.jpg","productName":"玩具小鸭子c","productPrice":"168","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":34,"orderid":"201604081443461","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":35,"orderid":"201604081444162","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":36,"orderid":"201604081500023","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":37,"orderid":"201604081513054","pid":3,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/3.jpg","productName":"男士孕妇服","productPrice":"300","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":38,"orderid":"201604081623371","pid":2,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":39,"orderid":"201604081623371","pid":4,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/4.jpg","productName":"玩具小鸭子c","productPrice":"168","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":40,"orderid":"201604081623371","pid":5,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/5.jpg","productName":"玩具汽车b","productPrice":"108","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":41,"orderid":"201604081640141","pid":2,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":42,"orderid":"201604141056521","pid":1,"pnum":2,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":43,"orderid":"201604141058131","pid":2,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":44,"orderid":"201604141103122","pid":1,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":45,"orderid":"201604141103543","pid":2,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":46,"orderid":"201604141105364","pid":2,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":47,"orderid":"201604141106175","pid":3,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/3.jpg","productName":"男士孕妇服","productPrice":"300","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":48,"orderid":"201604141110376","pid":1,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":49,"orderid":"201604141139501","pid":3,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/3.jpg","productName":"男士孕妇服","productPrice":"300","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":50,"orderid":"201604141209361","pid":1,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/1.jpg","productName":"灰色孕妇服","productPrice":"50","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":51,"orderid":"201604141211062","pid":2,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/2.jpg","productName":"孕妇裙","productPrice":"1","productPropertyName":"","state":1,"user_id":"14599337416420"},{"id":52,"orderid":"201604141212053","pid":4,"pnum":1,"ppid":0,"productImageUrl":"/images/topic/product/4.jpg","productName":"玩具小鸭子c","productPrice":"168","productPropertyName":"","state":1,"user_id":"14599337416420"}]
     * response : cart
     */

    /**
     * id : 29
     * orderid : 00000000000000000001
     * pid : 1
     * pnum : 2
     * ppid : 0
     * productImageUrl : /images/topic/product/1.jpg
     * productName : 灰色孕妇服
     * productPrice : 50
     * productPropertyName :
     * state : 1
     * user_id : 14599337416420
     */

    private List<CartBean> cart;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean implements Parcelable {
        private boolean isCheck;
        private String  id;
        private String  orderid;
        private String  pid;
        private String  pnum;
        private String  ppid;
        private String  productImageUrl;
        private String  productName;
        private String  productPrice;
        private String  productPropertyName;
        private String  state;
        private String  user_id;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
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

        public String getProductPropertyName() {
            return productPropertyName;
        }

        public void setProductPropertyName(String productPropertyName) {
            this.productPropertyName = productPropertyName;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(isCheck ? (byte) 1 : (byte) 0);
            dest.writeString(this.id);
            dest.writeString(this.orderid);
            dest.writeString(this.pid);
            dest.writeString(this.pnum);
            dest.writeString(this.ppid);
            dest.writeString(this.productImageUrl);
            dest.writeString(this.productName);
            dest.writeString(this.productPrice);
            dest.writeString(this.productPropertyName);
            dest.writeString(this.state);
            dest.writeString(this.user_id);
        }

        public CartBean() {
        }

        private CartBean(Parcel in) {
            this.isCheck = in.readByte() != 0;
            this.id = in.readString();
            this.orderid = in.readString();
            this.pid = in.readString();
            this.pnum = in.readString();
            this.ppid = in.readString();
            this.productImageUrl = in.readString();
            this.productName = in.readString();
            this.productPrice = in.readString();
            this.productPropertyName = in.readString();
            this.state = in.readString();
            this.user_id = in.readString();
        }

        public static final Parcelable.Creator<CartBean> CREATOR = new Parcelable.Creator<CartBean>() {
            public CartBean createFromParcel(Parcel source) {
                return new CartBean(source);
            }

            public CartBean[] newArray(int size) {
                return new CartBean[size];
            }
        };
    }
}
