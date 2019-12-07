package com.androidlmy.makecolor.customview;

import android.content.Context;
import android.graphics.Color;

import com.zyao89.view.zloading.ZLoadingDialog;

import static com.zyao89.view.zloading.Z_TYPE.STAR_LOADING;

/**
 * @功能:加载提示弹框
 * @Creat 2019/07/16 16:04
 * @User Lmy
 * @By Android Studio
 */
public class LoadingDialog {
    private static ZLoadingDialog dialog;

    public static void showLoading(Context context) {
        dialog = new ZLoadingDialog(context);
        dialog.setLoadingBuilder(STAR_LOADING)//设置类型
                .setLoadingColor(0xCC111111)//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//设置不可取消
//                .setDialogBackgroundColor(Color.parseColor("#")) // 设置背景色，默认白色
                .show();
    }

    public static void showLoading(Context context, String string) {
        dialog = new ZLoadingDialog(context);
        dialog.setLoadingBuilder(STAR_LOADING)//设置类型
                .setLoadingColor(0xCC111111)//颜色
                .setHintText(string)
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//设置不可取消
//                .setDialogBackgroundColor(Color.parseColor("#")) // 设置背景色，默认白色
                .show();
    }

    public static void dismissLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
