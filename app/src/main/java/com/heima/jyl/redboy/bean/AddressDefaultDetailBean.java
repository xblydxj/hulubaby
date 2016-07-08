package com.heima.jyl.redboy.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @创建者 种金币
 * @创建时间 2016/6/18 14:44
 * @描述 获取默认地址的Bean
 */
public class AddressDefaultDetailBean extends BaseBean{

    /**
     * addressArea : 密云县
     * addressDetail : 5555
     * city : 县
     * default : false
     * id : 144
     * name : 555
     * phoneNumber : 5555
     * province : 北京市
     * userid : 14599337416420
     * zipCode : 5555
     */

    private AddressBean address;
    /**
     * address : {"addressArea":"密云县","addressDetail":"5555","city":"县","default":false,"id":144,"name":"555","phoneNumber":"5555","province":"北京市","userid":"14599337416420","zipCode":"5555"}
     * response : addressDefaultDetail
     */

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class AddressBean {
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
