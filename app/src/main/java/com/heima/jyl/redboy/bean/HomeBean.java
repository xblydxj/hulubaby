package com.heima.jyl.redboy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @创建者 种金币
 * @创建时间 2016/6/15 8:31
 * @描述 ${TODO}
 */
public class HomeBean {

    /**
     * homeTopic : [{"id":123,"pic":"/images/home/image1.jpg","title":"活动1"},{"id":124,"pic":"/images/home/image2.jpg","title":"活动2"},{"id":125,"pic":"/images/home/image3.jpg","title":"活动3"},{"id":126,"pic":"/images/home/image4.jpg","title":"活动1"},{"id":127,"pic":"/images/home/image5.jpg","title":"活动2"},{"id":128,"pic":"/images/home/image6.jpg","title":"活动3"}]
     * response : home
     */
    @SerializedName("response")
    private String response;
    /**
     * id : 123
     * pic : /images/home/image1.jpg
     * title : 活动1
     */
    @SerializedName("homeTopic")
    private List<HomeTopicBean> homeTopic;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<HomeTopicBean> getHomeTopic() {
        return homeTopic;
    }

    public void setHomeTopic(List<HomeTopicBean> homeTopic) {
        this.homeTopic = homeTopic;
    }

    public static class HomeTopicBean {
        private int    id;
        private String pic;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "HomeTopicBean{" +
                    "id=" + id +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "response='" + response + '\'' +
                ", homeTopic=" + homeTopic +
                '}';
    }
}
