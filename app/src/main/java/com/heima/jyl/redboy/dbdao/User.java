package com.heima.jyl.redboy.dbdao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//定义表名
@DatabaseTable(tableName = "user")
public class User {
    //定义id，
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "pid",unique = true)
    private int pid;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "price")
    private int price;
    @DatabaseField(columnName = "MtPrice")
    private int MtPrice;
    @DatabaseField(columnName = "picRes")
    private String picRes;
    //无参数构造函数必须要，否则会报错
    public User() {
    }

    public User(int price, int pid, String picRes, String name, int mtPrice) {
        this.price = price;
        this.pid = pid;
        this.picRes = picRes;
        this.name = name;
        this.MtPrice = mtPrice;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", MtPrice=" + MtPrice +
                ", picRes='" + picRes + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMtPrice() {
        return MtPrice;
    }

    public void setMtPrice(int mtPrice) {
        MtPrice = mtPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicRes() {
        return picRes;
    }

    public void setPicRes(String picRes) {
        this.picRes = picRes;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}