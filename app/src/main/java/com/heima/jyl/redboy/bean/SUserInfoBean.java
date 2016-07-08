package com.heima.jyl.redboy.bean;

/**
 * Created by ting说你跳 on 2016/6/18.
 */
public class SUserInfoBean extends BaseBean{

    /**
     * response : response
     * userInfo : {"bonus":0,"couponid":0,"favoritesCount":2,"id":49,"level":"普通会员","orderCount":1,"password":"123","token":1460602523947,"user_id":"14599337416420","username":"123@123"}
     */
    /**
     * bonus : 0
     * couponid : 0
     * favoritesCount : 2
     * id : 49
     * level : 普通会员
     * orderCount : 1
     * password : 123
     * token : 1460602523947
     * user_id : 14599337416420
     * username : 123@123
     */

    private UserInfoBean userInfo;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        private int    bonus;
        private int    couponid;
        private int    favoritesCount;
        private int    id;
        private String level;
        private int    orderCount;
        private String password;
        private long   token;
        private String user_id;
        private String username;

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getCouponid() {
            return couponid;
        }

        public void setCouponid(int couponid) {
            this.couponid = couponid;
        }

        public int getFavoritesCount() {
            return favoritesCount;
        }

        public void setFavoritesCount(int favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getToken() {
            return token;
        }

        public void setToken(long token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
