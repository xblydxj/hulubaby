package com.heima.jyl.redboy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @创建者 种金币
 * @创建时间 2016/6/19 20:17
 * @描述 返回地址Bean
 */
public class BackAddressBean implements Parcelable {
    private String name;
    private String phone;
    private String address;
    private int addressId;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeInt(this.addressId);
    }

    public BackAddressBean() {
    }

    private BackAddressBean(Parcel in) {
        this.name = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.addressId = in.readInt();
    }

    public static final Parcelable.Creator<BackAddressBean> CREATOR = new Parcelable.Creator<BackAddressBean>() {
        public BackAddressBean createFromParcel(Parcel source) {
            return new BackAddressBean(source);
        }

        public BackAddressBean[] newArray(int size) {
            return new BackAddressBean[size];
        }
    };
}
