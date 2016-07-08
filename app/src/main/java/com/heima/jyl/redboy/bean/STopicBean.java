package com.heima.jyl.redboy.bean;

import java.util.List;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class STopicBean {


    /**
     * response : topic
     * total : 123
     *  topic  : [{"id":"专题活动ID","name":"专题活动名称","pic":"图片URL"},{"id":"专题活动ID","name":"专题活动名称","pic":"图片URL"}]
     */

    private String response;
    private int total;
    /**
     * id : 专题活动ID
     * name : 专题活动名称
     * pic : 图片URL
     */

    private List<TopicBean> topic;

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public static class TopicBean {
        private String id;
        private String name;
        private String pic;

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
    }
}
