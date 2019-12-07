package com.androidlmy.makecolor.bean.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * author: Liming
 * Date: 2019/8/7 10:29
 * Created by Android Studio.
 */
public class LoginLitepal extends LitePalSupport {

    private String user_id;
    private String user_name;
    private String phone;
    private String token;
    private String headurl;

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginLitepal{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", headurl='" + headurl + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public LoginLitepal(String user_id, String user_name, String phone, String token, String headurl) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.phone = phone;
        this.token = token;
        this.headurl = headurl;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
