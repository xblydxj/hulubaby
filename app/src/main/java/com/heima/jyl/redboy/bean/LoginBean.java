package com.heima.jyl.redboy.bean;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class LoginBean {

    /**
     * response : response
     * userInfo : {"bonus":0,"couponid":0,"favoritesCount":0,"id":1,"level":"普通会员","logState":false,"orderCount":0,"password":"123","token":1465999278651,"user_id":"2042822","username":"itcast"}
     */

    private String response;
    /**
     * bonus : 0
     * couponid : 0
     * favoritesCount : 0
     * id : 1
     * level : 普通会员
     * logState : false
     * orderCount : 0
     * password : 123
     * token : 1465999278651
     * user_id : 2042822
     * username : itcast
     */

    private UserInfoBean userInfo;
    /**
     * code : -3
     * msg : 用户不存在！
     */

    private ErrorBean error;

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

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class UserInfoBean {
        private int bonus;
        private int couponid;
        private int favoritesCount;
        private int id;
        private String level;
        private boolean logState;
        private int orderCount;
        private String password;
        private long token;
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

        public boolean isLogState() {
            return logState;
        }

        public void setLogState(boolean logState) {
            this.logState = logState;
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

    public static class ErrorBean {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
