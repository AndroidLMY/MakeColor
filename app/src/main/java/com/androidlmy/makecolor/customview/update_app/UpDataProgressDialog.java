package com.androidlmy.makecolor.customview.update_app;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.androidlmy.makecolor.R;

/**
 * @功能:更新进度弹框
 * @Creat 2019/07/16 15:04
 * @User Lmy
 * @By Android Studio
 */
public class UpDataProgressDialog extends Dialog {
    private Context mContext;
    private static UpDataProgressDialog alertDialog;
    private CustomHorizontalProgresWithNum horizontalProgress2;
    private TextView tvTitle;
    private boolean isLong = false;


    public static UpDataProgressDialog getInstance(Context context) {
        return alertDialog = new UpDataProgressDialog(context);
    }

    //在构造方法里预加载我们的样式，这样就不用每次创建都指定样式了
    public UpDataProgressDialog(Context context) {
        this(context, R.style.AlertDialog);

    }

    public UpDataProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        setContentView(R.layout.updataprogressdialog);
        horizontalProgress2 = findViewById(R.id.horizontalProgress2);
        tvTitle = findViewById(R.id.tv_title);


        horizontalProgress2.setProgress(0);
        horizontalProgress2.setMax(100);
    }

    /**
     * 设置进度
     */
    public UpDataProgressDialog setProgress(int progress) {
        horizontalProgress2.setProgress(progress);
        return this;
    }

    /**
     * 设置进度
     */
    public UpDataProgressDialog setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }


    /**
     * 设置是否可以取消dialog，由于直接使用setCancelable返回的是Dialog，所以自定义方法
     */
    public UpDataProgressDialog setDialogCancelable(boolean flag) {
        setCancelable(flag);
        return this;
    }

}
