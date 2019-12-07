package com.androidlmy.makecolor.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.androidlmy.makecolor.R;


/**
 * @功能:警告提示框dialog
 * @Creat 2019/07/16 15:04
 * @User Lmy
 * @By Android Studio
 */
public class AlertDialog extends Dialog {
    private TextView mTvTitle;
    private TextView mTvMessage;
    private TextView mTvNegativeButton;
    private TextView mTvPositiveButton;
    private boolean isLong = false;


    private Context mContext;

    private static AlertDialog alertDialog;

    public static AlertDialog getInstance(Context context) {
        return alertDialog = new AlertDialog(context);
    }

    //在构造方法里预加载我们的样式，这样就不用每次创建都指定样式了
    public AlertDialog(Context context) {
        this(context, R.style.AlertDialog);

    }

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        setContentView(R.layout.alertdialog);
        mTvTitle = findViewById(R.id.tv_title);
        mTvMessage = findViewById(R.id.tv_message);
        mTvNegativeButton = findViewById(R.id.tv_negativeButton);
        mTvPositiveButton = findViewById(R.id.tv_positiveButton);
    }


    /**
     * 设置标题栏
     */
    public AlertDialog setTitle(String title) {
        if (title != null) {
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置内容
     */
    public AlertDialog setMessage(String msg) {
        if (msg != null) {
            mTvMessage.setText(msg);
            mTvMessage.setVisibility(View.VISIBLE);
            if (isLong) {
                //设置内容,当内容比较多时，需要设置gravity为Gravity.START属性
                mTvMessage.setGravity(Gravity.START);
            }
        }
        return this;
    }

    /**
     * 是否兼容文字过长的显示问题
     */
    public AlertDialog setisLong(boolean isLong) {
        this.isLong = isLong;
        return this;
    }


    /**
     * 设置右边确定点击按钮
     *
     * @param text     按钮上的显示字
     * @param listener 点击事件监听
     */
    public AlertDialog setPositiveButton(final String text, final OnMyDialogButtonClickListener listener) {
        if (text != null) {
            mTvPositiveButton.setText(text);
        }
        mTvPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick();
                    dismiss();

                }

            }
        });
        return this;
    }

    public interface OnMyDialogButtonClickListener {
        void onClick();
    }


    /**
     * 设置右边确定点击按钮
     * 默认 点击 字体 为 确定
     *
     * @param listener 点击事件监听
     */
    public AlertDialog setPositiveButton(final OnMyDialogButtonClickListener listener) {

        return setPositiveButton(null, listener);
    }

    /**
     * 设置左边取消点击按钮
     *
     * @param text     按钮上的显示字
     * @param listener 点击事件监听
     */
    public AlertDialog setNegativeButton(String text, final OnMyDialogButtonClickListener listener) {
        if (text != null) {
            mTvNegativeButton.setText(text);
        }
        mTvNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick();
                    dismiss();
                }


            }
        });
        return this;
    }

    /**
     * 设置是否可以取消dialog，由于直接使用setCancelable返回的是Dialog，所以自定义方法
     */
    public AlertDialog setDialogCancelable(boolean flag) {
        setCancelable(flag);
        return this;
    }

}
