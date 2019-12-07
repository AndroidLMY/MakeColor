package com.androidlmy.makecolor.contract;

import android.content.Context;

import com.androidlmy.makecolor.bean.UpDataBean;
import com.androidlmy.makecolor.presenter.UpDataPresenter;

import java.util.Map;

/**
 * app更新接口
 * author: Liming
 * Date: 2019/8/10 15:43
 * Created by Android Studio.
 */
public interface UpDataContract {
    interface Model {
        /**
         * model调用接口获取数据
         */
        void getData(Context context, UpDataPresenter presenter, Map parms);
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
        void showUpDataSucceed(UpDataBean.DataBean dataBean);

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
        void showSucceed(UpDataBean.DataBean dataBean);

        /**
         * 设置请求参数
         */
        void setParms(Map parms);

        void showFail(String message);


    }
}
