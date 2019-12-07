package com.androidlmy.makecolor.contract;

import android.content.Context;

import com.androidlmy.makecolor.bean.FriendListBean;
import com.androidlmy.makecolor.presenter.FriendPresenter;

import java.util.List;
import java.util.Map;

/**
 * @功能:
 * @Creat 2019/11/27 15:40
 * @User Lmy
 * @Compony zaituvideo
 */
public interface FriendContract {
    interface Model {
        /**
         * model调用接口获取数据
         */
        void getData(Context context, FriendPresenter presenter, Map parms);
    }

    interface View {

        void showDialog();

        void dismissDialog();

        void showSucceed(List<FriendListBean.DataBean> friendListBeans);

        void showFail(String message);
    }

    interface Presenter {

        void getData();

        void showSucceed(List<FriendListBean.DataBean> friendListBeans);

        void setParms(Map parms);

        void showFail(String message);


    }
}
