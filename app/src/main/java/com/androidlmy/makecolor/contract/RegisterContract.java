package com.androidlmy.makecolor.contract;

import android.content.Context;

import com.androidlmy.makecolor.presenter.RegisterPresenter;

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
public interface RegisterContract {
    interface Model {
        void getData(Context context, RegisterPresenter presenter, Map parms,  MultipartBody.Part file);
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

        void setParms(Map parms,  MultipartBody.Part file);

        void showFail(String message);
    }
}
