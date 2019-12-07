package com.androidlmy.makecolor.bean;

import java.util.List;

/**
 * @功能:
 * @Creat 2019/11/27 15:41
 * @User Lmy
 * @Compony zaituvideo
 */
public class FriendListBean {

    /**
     * code : 200
     * data : [{"name":"他扣扣","phone":"17866699999","creat_time":"2019-11-27 15:24:34","token":"eI5ZxCno306tXlLJyegZLrx8NNCAx4FDf7XByrF4OM4iT89k2PL2M6zm/KfIChxPkUxg6VHXguRYTs8k4qvwPoEadzvdzEg3","user_id":"2B8SSWDW","headurl":"http://www.androidlmy.top:8080/images/ZDPBN6DA.jpg"},{"name":"李明洋","phone":"17739391441","creat_time":"2019-11-26 11:48:38","token":"xqMsbMWZO7BFg1GodixJnLx8NNCAx4FDf7XByrF4OM4iT89k2PL2M8NPo26livGp27PeTQIQCKV/EXIuAwQxmYEadzvdzEg3","user_id":"W576UI4X","headurl":"http://www.androidlmy.top:8080/images/LQ1SUZU4.jpg"},{"name":"小红","phone":"17739391445","creat_time":"2019-11-26 09:55:28","token":"eaQtY2jMDfc+MhNGSSQM2bx8NNCAx4FDf7XByrF4OM4iT89k2PL2MxYZ9+IhOM+FqB+UyQEawGui719eFGSvbYEadzvdzEg3","user_id":"W043ANKT","headurl":"http://www.androidlmy.top:8080/images/37WKKVZF.jpg"},{"name":"小明","phone":"17739391446","creat_time":"2019-11-26 09:55:15","token":"fbUDNhYNqe9SPwWELjcwTLx8NNCAx4FDf7XByrF4OM4iT89k2PL2M69brv9X/74XUqyVI+Akb9c+5+Xt1HpqgIEadzvdzEg3","user_id":"L1RMRFH2","headurl":"http://www.androidlmy.top:8080/images/37WKKVZF.jpg"}]
     * message : 查询成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 他扣扣
         * phone : 17866699999
         * creat_time : 2019-11-27 15:24:34
         * token : eI5ZxCno306tXlLJyegZLrx8NNCAx4FDf7XByrF4OM4iT89k2PL2M6zm/KfIChxPkUxg6VHXguRYTs8k4qvwPoEadzvdzEg3
         * user_id : 2B8SSWDW
         * headurl : http://www.androidlmy.top:8080/images/ZDPBN6DA.jpg
         */

        private String name;
        private String phone;
        private String creat_time;
        private String token;
        private String user_id;
        private String headurl;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }
    }
}
