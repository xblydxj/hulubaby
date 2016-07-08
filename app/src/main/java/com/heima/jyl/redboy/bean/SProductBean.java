package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class SProductBean {

    /**
     * available : true
     * bigPic : ["/images/product/detail/bigcar3.jpg"]
     * buyLimit : 10
     * commentCount : 3
     * id : 3
     * inventoryArea : 大武汉
     * leftTime : 1447084515
     * limitPrice : 90
     * marketPrice : 100
     * name : 男士孕妇服
     * pics : ["/images/product/detail/qun3.jpg","/images/product/detail/car1.jpg","/images/product/detail/car4.jpg"]
     * price : 300
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}]
     * score : 3
     */

    private ProductBean product;
    /**
     * product : {"available":true,"bigPic":["/images/product/detail/bigcar3.jpg"],"buyLimit":10,"commentCount":3,"id":3,"inventoryArea":"大武汉","leftTime":1447084515,"limitPrice":90,"marketPrice":100,"name":"男士孕妇服","pics":["/images/product/detail/qun3.jpg","/images/product/detail/car1.jpg","/images/product/detail/car4.jpg"],"price":300,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":3}
     * response : product
     */

    private String response;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class ProductBean {
        private boolean available;
        private int buyLimit;
        private int commentCount;
        private int id;
        private String inventoryArea;
        private int leftTime;
        private int limitPrice;
        private int marketPrice;
        private String name;
        private int price;
        private int score;
        private List<String> bigPic;
        private List<String> pics;
        /**
         * id : 1
         * k : 颜色
         * v : 红色
         */

        private List<ProductPropertyBean> productProperty;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<String> getBigPic() {
            return bigPic;
        }

        public void setBigPic(List<String> bigPic) {
            this.bigPic = bigPic;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public List<ProductPropertyBean> getProductProperty() {
            return productProperty;
        }

        public void setProductProperty(List<ProductPropertyBean> productProperty) {
            this.productProperty = productProperty;
        }

        public static class ProductPropertyBean {
            private int id;
            private String k;
            private String v;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getK() {
                return k;
            }

            public void setK(String k) {
                this.k = k;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }
        }
    }
}
