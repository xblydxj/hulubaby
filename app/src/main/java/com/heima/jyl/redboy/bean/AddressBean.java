package com.heima.jyl.redboy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/17.
 */
public class AddressBean extends BaseBean{

    /**
     * addressList : [{"addressArea":"abc","addressDetail":"abc","city":"abc","default":false,"id":133,"name":"abc","phoneNumber":"123","province":"abc","userid":"20428","zipCode":"12345"},{"addressArea":"洪山区文华路","addressDetail":"文华学院","city":"武汉市","default":false,"id":134,"name":"祝儒桥","phoneNumber":"18986104910","province":"湖北","userid":"20428","zipCode":"1008611"},{"addressArea":"洪山区街道口","addressDetail":"地铁c口","city":"武汉市","default":false,"id":139,"name":"itcast","phoneNumber":"10086","province":"湖北","userid":"20428","zipCode":"1008611"}]
     * response : response
     */

    /**
     * addressArea : abc
     * addressDetail : abc
     * city : abc
     * default : false
     * id : 133
     * name : abc
     * phoneNumber : 123
     * province : abc
     * userid : 20428
     * zipCode : 12345
     */

    private List<AddressListBean> addressList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

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
