package com.androidlmy.makecolor.contract;

import android.content.Context;

import com.androidlmy.makecolor.bean.LogBean;
import com.androidlmy.makecolor.presenter.LoginPresenter;

import java.util.Map;

/**
 * @功能:
 * @Creat 2019/11/14 10:29
 * @User Lmy
 * @Compony zaituvideo
 */
public interface LoginContract {
    interface Model {
        /**
         * model调用接口获取数据
         */
        void getData(Context context, LoginPresenter presenter, Map parms);
    }

    interface View {
        /**
         * view显示加载框
         */
        void showDialog();

        /**
         * view取消加载框
         */
        void dismissDialog();

        /**
         * 数据返回成功的回调
         */
        void showSucceed(LogBean logBean);

        /**
         * 接口数据错误
         */
        void showFail(String message);
    }

    interface Presenter {
        /**
         * presenter层调用
         */
        void getData();

        /**
         * 数据返回成功的回调
         */
        void showSucceed(LogBean logBean);

        /**
         * 设置请求参数
         */
        void setParms(Map parms);

        /**
         * 接口数据错误
         */
        void showFail(String message);


    }
}
