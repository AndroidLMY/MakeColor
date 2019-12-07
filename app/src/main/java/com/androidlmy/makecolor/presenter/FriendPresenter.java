package com.androidlmy.makecolor.presenter;

import android.content.Context;

import com.androidlmy.makecolor.bean.FriendListBean;
import com.androidlmy.makecolor.contract.FriendContract;
import com.androidlmy.makecolor.model.FriendModel;

import java.util.List;
import java.util.Map;

/**
 * @功能:
 * @Creat 2019/11/27 15:40
 * @User Lmy
 * @Compony zaituvideo
 */
public class FriendPresenter implements FriendContract.Presenter {
    private Context context;
    private FriendContract.View view;
    private FriendModel model;
    private Map parms;

    public FriendPresenter(Context context, FriendContract.View view) {
        this.context = context;
        this.view = view;
        model = new FriendModel();
    }

    @Override
    public void getData() {
        model.getData(context, this, parms);

    }

    @Override
    public void showSucceed(List<FriendListBean.DataBean> friendListBeans) {
        view.dismissDialog();
        view.showSucceed(friendListBeans);

    }

    @Override
    public void setParms(Map parms) {
        this.parms = parms;
    }

    @Override
    public void showFail(String message) {
        view.dismissDialog();
        view.showFail(message);

    }
}
