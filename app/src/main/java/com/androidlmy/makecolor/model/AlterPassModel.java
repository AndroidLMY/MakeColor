package com.androidlmy.makecolor.model;

import android.content.Context;

import com.androidlmy.makecolor.bean.CodeMessage;
import com.androidlmy.makecolor.contract.AlterPassContract;
import com.androidlmy.makecolor.net.ApiHelperl;
import com.androidlmy.makecolor.net.api.LoginApi;
import com.androidlmy.makecolor.net.okhttp.CallBack;
import com.androidlmy.makecolor.presenter.AlterPassPresenter;

import java.util.Map;

import retrofit2.Call;

/**
 * @功能:
 * @Creat 2019/11/22 14:28
 * @User Lmy
 * @Compony zaituvideo
 */
public class AlterPassModel implements AlterPassContract.Model {
    @Override
    public void getData(Context context, AlterPassPresenter presenter, Map parms) {
        Call<CodeMessage> call = ApiHelperl.getInstance().
                buildRetrofit().
                createService(LoginApi.class).
                alterpassword(parms);
        call.enqueue(new CallBack<CodeMessage>() {
            @Override
            public void onSuccess(CodeMessage response) {
                presenter.showSucceed(response.getMessage());
            }

            @Override
            public void onError(String message) {
                presenter.showFail(message);
            }
        });
    }
}
