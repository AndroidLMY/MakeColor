package com.androidlmy.makecolor.utils;

import com.androidlmy.makecolor.bean.litepal.LoginLitepal;

import org.litepal.LitePal;

import java.util.List;

/**
 * @功能:判断登录 存储登录数据逻辑
 * @Creat 2019/11/15 16:24
 * @User Lmy
 * @Compony zaituvideo
 */
public class LoginUtils {
    /**
     * 判断用户是否登录`
     */
    public static boolean isLogin() {
        LoginLitepal login = getLoginBean();
        if (login == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void clearLogin() {
        LitePal.deleteAll(LoginLitepal.class);
    }

    /**
     * 获取登录后保存的用户数据
     */
    public static String getUserID() {
        LoginLitepal login = getLoginBean();
        if (login == null) {
            return "";
        } else {
            return login.getUser_id();
        }
    }

    /**
     * 获取登录后保存的用户数据
     */
    public static LoginLitepal getLoginBean() {
        //查找movie表的所有记录，返回值是一个泛型为Movie的List集合
        List<LoginLitepal> logins = LitePal.findAll(LoginLitepal.class);
        if (logins.size() == 0) {
            return null;
        } else {
            return logins.get(0);
        }
    }

}
