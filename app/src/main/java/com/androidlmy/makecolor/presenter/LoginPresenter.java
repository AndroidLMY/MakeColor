package com.androidlmy.makecolor.presenter;

import android.content.Context;

import com.androidlmy.makecolor.bean.LogBean;
import com.androidlmy.makecolor.contract.LoginContract;
import com.androidlmy.makecolor.model.LoginModel;

import java.util.Map;

/**
 * @功能:
 * @Creat 2019/11/14 10:29
 * @User Lmy
 * @Compony zaituvideo
 */
public class LoginPresenter implements LoginContract.Presenter {
    private Context context;
    private LoginContract.View view;
    private LoginModel model;
    private Map parms;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void getData() {
        model.getData(context, this, parms);
        view.showDialog();
    }

    @Override
    public void showSucceed(LogBean logBean) {
        view.showSucceed(logBean);
        view.dismissDialog();
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
