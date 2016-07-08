package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class BrandProductListBean {

    /**
     * response : brandProductlist
     * productList : [{"id":"1102539","name":"雅培金装","pic":"","marketPrice":"79","price":"78"},{"id":"1102539","name":"雅培金装","pic":"","marketPrice":"79","price":"78"}]
     * listCount : 1500
     */

    private String response;
    private String listCount;
    /**
     * id : 1102539
     * name : 雅培金装
     * pic :
     * marketPrice : 79
     * price : 78
     */

    private List<ProductListBean> productList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        private String id;
        private String name;
        private String pic;
        private String marketPrice;
        private String price;

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

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
