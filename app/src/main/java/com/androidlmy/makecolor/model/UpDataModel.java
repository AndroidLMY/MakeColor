package com.androidlmy.makecolor.model;

import android.content.Context;

import com.androidlmy.makecolor.bean.UpDataBean;
import com.androidlmy.makecolor.contract.UpDataContract;
import com.androidlmy.makecolor.net.ApiHelperl;
import com.androidlmy.makecolor.net.okhttp.CallBack;
import com.androidlmy.makecolor.net.api.LoginApi;
import com.androidlmy.makecolor.presenter.UpDataPresenter;

import java.util.Map;

import retrofit2.Call;

/**
 * author: Liming
 * Date: 2019/8/10 15:43
 * Created by Android Studio.
 */
public class UpDataModel implements UpDataContract.Model {
    @Override
    public void getData(Context context, UpDataPresenter presenter, Map parms) {
        Call<UpDataBean> call = ApiHelperl
                .getInstance()
                .buildRetrofit()
                .createService(LoginApi.class)
                .updataapp(parms);



        call.enqueue(new CallBack<UpDataBean>() {
            @Override
            public void onSuccess(UpDataBean response) {
                presenter.showSucceed(response.getData());

            }

            @Override
            public void onError(String message) {
                presenter.showFail(message);

            }
        });
    }
}
