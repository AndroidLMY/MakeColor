package com.androidlmy.makecolor.presenter;

import android.content.Context;

import com.androidlmy.makecolor.contract.RegisterContract;
import com.androidlmy.makecolor.model.RegisterModel;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @功能:
 * @Creat 2019/11/22 9:43
 * @User Lmy
 * @Compony zaituvideo
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private Context context;
    private RegisterContract.View view;
    private RegisterModel model;
    private Map parms;
    private  MultipartBody.Part file;

    public RegisterPresenter(Context context, RegisterContract.View view) {
        this.context = context;
        this.view = view;
        model = new RegisterModel();
    }

    @Override
    public void getData() {
        model.getData(context, this, parms,file);
        view.showDialog();
    }

    @Override
    public void showSucceed(String message) {
        view.dismissDialog();
        view.showSucceed(message);
    }

    @Override
    public void setParms(Map parms,  MultipartBody.Part file) {
        this.parms = parms;
        this.file = file;
    }

    @Override
    public void showFail(String message) {
        view.dismissDialog();
        view.showFail(message);
    }
}
