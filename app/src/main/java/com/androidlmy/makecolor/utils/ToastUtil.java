package com.androidlmy.makecolor.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @功能:ToastUtil工具类
 * @Creat 2019/07/16 15:32
 * @User Lmy
 * @By Android Studio
 */
public class ToastUtil {
    private static Toast mToast;

    /**
     * 普通Toast 防止多点击功能
     *
     * @author :limingyang
     */
    public static void show(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();//注销之前显示的那条信息
            mToast = null;//这里要注意上一步相当于隐藏了信息，mtoast并没有为空，我们强制是他为空
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }
}
