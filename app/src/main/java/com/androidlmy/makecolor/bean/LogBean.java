package com.androidlmy.makecolor.bean;

/**
 * @功能:
 * @Creat 2019/11/14 15:35
 * @User Lmy
 * @Compony zaituvideo
 */
public class LogBean {

    /**
     * code : 200
     * data : {"name":"李明洋","phone":"17739391446","creat_time":"2019-11-12 16:27:09","id":"RODSWSZ9"}
     * message : 登录成功
     */
    private int code;
    private DataBean data;
    private String message;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * name : 李明洋
         * phone : 17739391446
         * creat_time : 2019-11-12 16:27:09
         * id : RODSWSZ9
         */

        private String name;
        private String phone;
        private String creat_time;
        private String user_id;
        private String token;
        private String headurl;

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreat_time() {
            return creat_time;
        }

        public void setCreat_time(String creat_time) {
            this.creat_time = creat_time;
        }

    }
}
