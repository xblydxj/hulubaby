package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * @创建者 种金币
 * @创建时间 2016/6/14 21:07
 * @描述 搜索推荐的Bean
 */
public class SearchreCommendBean {

    /**
     * response : searchrecommend
     * searchKeywords : ["玩具","男士","孕妇"]
     */

    private String response;
    private List<String> searchKeywords;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
