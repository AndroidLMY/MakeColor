package com.androidlmy.makecolor.contract;

import android.content.Context;

import com.androidlmy.makecolor.presenter.AlterPassPresenter;

import java.util.Map;

/**
 * @功能:
 * @Creat 2019/11/22 14:28
 * @User Lmy
 * @Compony zaituvideo
 */
public interface AlterPassContract {
    interface Model {
        /**
         * model调用接口获取数据
         */
        void getData(Context context, AlterPassPresenter presenter, Map parms);
    }

    interface View {

        void showDialog();

        void dismissDialog();

        void showSucceed(String message);

        void showFail(String message);
    }

    interface Presenter {

        void getData();

        void showSucceed(String message);

        void setParms(Map parms);

        void showFail(String message);


    }
}
