package com.androidlmy.makecolor.model;

import android.content.Context;

import com.androidlmy.makecolor.bean.CodeMessage;
import com.androidlmy.makecolor.contract.RegisterContract;
import com.androidlmy.makecolor.net.ApiHelperl;
import com.androidlmy.makecolor.net.api.LoginApi;
import com.androidlmy.makecolor.net.okhttp.CallBack;
import com.androidlmy.makecolor.presenter.RegisterPresenter;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @功能:
 * @Creat 2019/11/22 9:43
 * @User Lmy
 * @Compony zaituvideo
 */
public class RegisterModel implements RegisterContract.Model {
    @Override
    public void getData(Context context, RegisterPresenter presenter, Map parms, MultipartBody.Part file) {
        Call<CodeMessage> call = ApiHelperl.getInstance().
                buildRetrofit().
                createService(LoginApi.class).
                register(parms, file);
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
