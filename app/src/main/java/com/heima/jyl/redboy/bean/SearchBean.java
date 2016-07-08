package com.heima.jyl.redboy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @创建者 种金币
 * @创建时间 2016/6/14 21:16
 * @描述 搜索商品列表的Bean
 */
public class SearchBean {

    /**
     * listCount : 10
     * productList : [{"id":4,"marketPrice":198,"name":"玩具小鸭子c","pic":"/images/topic/product/4.jpg","price":168},{"id":5,"marketPrice":98,"name":"玩具汽车b","pic":"/images/topic/product/5.jpg","price":108},{"id":6,"marketPrice":43,"name":"玩具积木a","pic":"/images/topic/product/6.jpg","price":52},{"id":7,"marketPrice":43,"name":"玩具积木t","pic":"/images/topic/product/6.jpg","price":59},{"id":8,"marketPrice":33,"name":"玩具积木c","pic":"/images/topic/product/5.jpg","price":66},{"id":9,"marketPrice":43,"name":"玩具积木d","pic":"/images/topic/product/6.jpg","price":55},{"id":10,"marketPrice":52,"name":"玩具积木e","pic":"/images/topic/product/6.jpg","price":65},{"id":11,"marketPrice":43,"name":"玩具积木f","pic":"/images/topic/product/6.jpg","price":58},{"id":12,"marketPrice":46,"name":"玩具积木g","pic":"/images/topic/product/6.jpg","price":57},{"id":13,"marketPrice":55,"name":"玩具积木h","pic":"/images/topic/product/6.jpg","price":63}]
     * response : search
     * total : 10
     */

    private int                   listCount;

    private String                response;

    private int                   total;
    /**
     * id : 4
     * marketPrice : 198
     * name : 玩具小鸭子c
     * pic : /images/topic/product/4.jpg
     * price : 168
     */

    private List<ProductListBean> productList;

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

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
        @SerializedName("id")
        int  id;
        int  marketPrice;
        String name;
        String pic;
        int    price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        @Override
        public String toString() {
            return "ProductListBean{" +
                    "id=" + id +
                    ", marketPrice=" + marketPrice +
                    ", name='" + name + '\'' +
                    ", pic='" + pic + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

}
