package com.androidlmy.makecolor.model;

import android.content.Context;

import com.androidlmy.makecolor.bean.LogBean;
import com.androidlmy.makecolor.contract.LoginContract;
import com.androidlmy.makecolor.net.okhttp.CallBack;
import com.androidlmy.makecolor.net.api.LoginApi;
import com.androidlmy.makecolor.net.ApiHelperl;
import com.androidlmy.makecolor.presenter.LoginPresenter;

import java.util.Map;

import retrofit2.Call;

/**
 * @功能:
 * @Creat 2019/11/14 10:29
 * @User Lmy
 * @Compony zaituvideo
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public void getData(Context context, LoginPresenter presenter, Map parms) {
        Call<LogBean> call = ApiHelperl.getInstance().
                buildRetrofit().
                createService(LoginApi.class).
                login(parms);
        call.enqueue(new CallBack<LogBean>() {
            @Override
            public void onSuccess(LogBean response) {
                presenter.showSucceed(response);
            }

            @Override
            public void onError(String message) {
                presenter.showFail(message);
            }
        });
    }
}
