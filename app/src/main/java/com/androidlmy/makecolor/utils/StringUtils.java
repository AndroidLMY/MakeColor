package com.androidlmy.makecolor.utils;


import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @功能: String 工具类
 * @Creat 2019/07/11 14:05
 * @User Lmy
 * @By Android Studio
 */
public class StringUtils {
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0 || str.equals("null"));
    }

    /**
     * 代替字符串
     *
     * @param content
     * @param oldStr
     * @param newStr
     * @return
     */
    public static String replaceAllStr(String content, String oldStr,
                                       String newStr) {
        if (StringUtils.isBlank(content)) {
            return "";
        }
        String replace = content.replace(oldStr, newStr);
        return replace;
    }

    /**
     * 省略小数点
     *
     * @return
     */
    public static String getTwoDecimal(double Number) {
        double parseDouble = Double.parseDouble(new DecimalFormat("#.##")
                .format(Number / 1000));
        return String.valueOf(parseDouble);
    }

    /**
     * 分割字符串
     *
     * @param cotent
     * @param limit
     * @return
     */
    public static String[] SqliteStr(String cotent, String limit) {
        if (StringUtils.isBlank(cotent)) {
            return null;
        }
        String[] split = cotent.split(limit);
        return split;
    }


    /**
     * 字符串转化成数字
     *
     * @param str
     * @return
     */
    public static int strToNum(String str) {
        int valueOf = 0;
        if (StringUtils.isBlank(str)) {
            return valueOf;
        }
        try {
            valueOf = Integer.parseInt(str);
        } catch (Exception e) {
            // TODO: handle exception
            valueOf = 0;
        }

        return valueOf;
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[0-9][0-9][0-9]{8,11}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis()).substring(0, 10);
    }

    /**
     * 获取随机数字
     *
     * @return
     */
    public static String getRandom() {
        return String.valueOf(Math.abs(new Random().nextLong()));
    }

    /**
     * 获取某字段所在的位置
     *
     * @param mContent
     * @param parm
     * @return
     */
    public static int getIndex(String mContent, String parm) {
        return mContent.indexOf(parm);
    }


    /**
     * 字符串数组转化成数字数组
     *
     * @param numStr
     * @return
     */
    public static int[] getNumArr(String numStr) {
        if (isBlank(numStr)) {
            return null;
        }
        String[] split = numStr.split(",");
        int[] splitInt = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            splitInt[i] = Integer.parseInt(split[i]);
        }
        return splitInt;
    }





}