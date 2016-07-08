package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * Created by ting说你跳 on 2016/6/15.
 */
public class FavoritesBean {

    /**
     * productList : [{"available":true,"buyLimit":10,"commentCount":2,"id":2,"inventoryArea":"全国","isHot":2,"isNew":2,"leftTime":1448004566,"limitPrice":1,"marketPrice":28,"name":"孕妇裙","pic":"/images/topic/product/2.jpg","price":1,"productDesc":"好商品","product_property_id":"1,2,3,4,5","saleNum":1,"score":2,"shelves":1439543897925},{"available":true,"buyLimit":10,"commentCount":3,"id":3,"inventoryArea":"大武汉","isHot":3,"isNew":3,"leftTime":1447084515,"limitPrice":90,"marketPrice":100,"name":"男士孕妇服","pic":"/images/topic/product/3.jpg","price":300,"productDesc":"好商品","product_property_id":"1,2,3,4,5","saleNum":90,"score":3,"shelves":1439543827925}]
     * response : favorites
     * total : 2
     */

    private String response;
    private int total;
    /**
     * available : true
     * buyLimit : 10
     * commentCount : 2
     * id : 2
     * inventoryArea : 全国
     * isHot : 2
     * isNew : 2
     * leftTime : 1448004566
     * limitPrice : 1
     * marketPrice : 28
     * name : 孕妇裙
     * pic : /images/topic/product/2.jpg
     * price : 1
     * productDesc : 好商品
     * product_property_id : 1,2,3,4,5
     * saleNum : 1
     * score : 2
     * shelves : 1439543897925
     */

    private List<ProductListBean> productList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        private boolean available;
        private int     buyLimit;
        private int     commentCount;
        private int     id;
        private String  inventoryArea;
        private int     isHot;
        private int     isNew;
        private int     leftTime;
        private int     limitPrice;
        private int     marketPrice;
        private String  name;
        private String  pic;
        private int     price;
        private String  productDesc;
        private String  product_property_id;
        private int     saleNum;
        private int     score;
        private long    shelves;

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public int getBuyLimit() {
            return buyLimit;
        }

        public void setBuyLimit(int buyLimit) {
            this.buyLimit = buyLimit;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInventoryArea() {
            return inventoryArea;
        }

        public void setInventoryArea(String inventoryArea) {
            this.inventoryArea = inventoryArea;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public int getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(int limitPrice) {
            this.limitPrice = limitPrice;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getProductDesc() {
            return productDesc;
        }

        public void setProductDesc(String productDesc) {
            this.productDesc = productDesc;
        }

        public String getProduct_property_id() {
            return product_property_id;
        }

        public void setProduct_property_id(String product_property_id) {
            this.product_property_id = product_property_id;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public long getShelves() {
            return shelves;
        }

        public void setShelves(long shelves) {
            this.shelves = shelves;
        }
    }
}
