package com.heima.jyl.redboy.bean;

/**
 * Created by Jyl on 2016/6/15.
 */
public class HomeCategoryBean {
    private int imgresid;
    private String title;

    public HomeCategoryBean() {
    }

    public HomeCategoryBean(int imgresid, String title) {
        super();
        this.imgresid = imgresid;
        this.title = title;
    }

    public int getImgresid() {
        return imgresid;
    }

    public void setImgresid(int imgresid) {
        this.imgresid = imgresid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
