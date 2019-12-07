package com.androidlmy.makecolor.model;

import android.content.Context;

import com.androidlmy.makecolor.bean.CodeMessage;
import com.androidlmy.makecolor.bean.FriendListBean;
import com.androidlmy.makecolor.bean.UpDataBean;
import com.androidlmy.makecolor.contract.FriendContract;
import com.androidlmy.makecolor.net.ApiHelperl;
import com.androidlmy.makecolor.net.api.LoginApi;
import com.androidlmy.makecolor.net.okhttp.CallBack;
import com.androidlmy.makecolor.presenter.FriendPresenter;

import java.util.Map;

import retrofit2.Call;

/**
 * @功能:
 * @Creat 2019/11/27 15:40
 * @User Lmy
 * @Compony zaituvideo
 */
public class FriendModel implements FriendContract.Model {
    @Override
    public void getData(Context context, FriendPresenter presenter, Map parms) {
        Call<FriendListBean> call = ApiHelperl
                .getInstance()
                .buildRetrofit()
                .createService(LoginApi.class)
                .selectall();
        call.enqueue(new CallBack<FriendListBean>() {
            @Override
            public void onSuccess(FriendListBean response) {
                presenter.showSucceed(response.getData());

            }

            @Override
            public void onError(String message) {
                presenter.showFail(message);

            }
        });
    }
}
