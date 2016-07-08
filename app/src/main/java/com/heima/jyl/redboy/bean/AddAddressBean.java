package com.heima.jyl.redboy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public class AddAddressBean extends BaseBean {

    /**
     * addressArea : 010305
     * addressDetail : éµåºè·¯3å·çº¢å­©å­
     * city : æ­¦æ±å¸
     * default : false
     * id : 145
     * name : èæ
     * phoneNumber : 15801477821
     * province : æ¹åç
     * userid : 14662156000790
     * zipCode : 100195
     */

    private List<AddressListBean> addressList;

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean {
        private String  addressArea;
        private String  addressDetail;
        private String  city;
        @SerializedName("default")
        private boolean defaultX;
        private int     id;
        private String  name;
        private String  phoneNumber;
        private String  province;
        private String  userid;
        private String  zipCode;

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public boolean isDefaultX() {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
