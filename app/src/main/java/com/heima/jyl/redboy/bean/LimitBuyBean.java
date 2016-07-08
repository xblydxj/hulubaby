package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class LimitBuyBean {

    /**
     * response : limitbuy
     * “total” : 180
     * productList : [{"id":"1102539","name":"雅培金装","pic":"","price":"79","limitPrice":"78","leftTime":"3600"},{"id":"1102539","name":"雅培金装","pic":"","price":"79","limitPrice":"78","leftTime":"3600"}]
     * listCount : 15
     */

    private String response;
    /**
     * total : 180
     * productList : [{"id":"1102539","name":"雅培金装","pic":"","price":"79","limitPrice":"78","leftTime":"3600"},{"id":"1102539","name":"雅培金装","pic":"","price":"79","limitPrice":"78","leftTime":"3600"}]
     * listCount : 15
     */

    private int total;
    private String listCount;
    /**
     * id : 1102539
     * name : 雅培金装
     * pic :
     * price : 79
     * limitPrice : 78
     * leftTime : 3600
     */

    private List<LimitbuyListBean> productList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }

    public List<LimitbuyListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<LimitbuyListBean> productList) {
        this.productList = productList;
    }

    public static class LimitbuyListBean {
        private String id;
        private String name;
        private String pic;
        private String price;
        private String limitPrice;
        private String leftTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLimitPrice() {
            return limitPrice;
        }

        public void setLimitPrice(String limitPrice) {
            this.limitPrice = limitPrice;
        }

        public String getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(String leftTime) {
            this.leftTime = leftTime;
        }
    }
}
