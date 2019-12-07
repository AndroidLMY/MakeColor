package com.androidlmy.makecolor.presenter;

import android.content.Context;

import com.androidlmy.makecolor.bean.UpDataBean;
import com.androidlmy.makecolor.contract.UpDataContract;
import com.androidlmy.makecolor.model.UpDataModel;

import java.util.HashMap;
import java.util.Map;

/**
 * author: Liming
 * Date: 2019/8/10 15:43
 * Created by Android Studio.
 */
public class UpDataPresenter implements UpDataContract.Presenter {
    private Context context;
    private UpDataContract.View view;
    private UpDataModel model;
    private Map parms=new HashMap();

    public UpDataPresenter(Context context, UpDataContract.View view) {
        this.context = context;
        this.view = view;
        model = new UpDataModel();
    }

    @Override
    public void getData() {

        model.getData(context, this, parms);
    }

    @Override
    public void showSucceed(UpDataBean.DataBean dataBean) {
        view.showUpDataSucceed(dataBean);
        view.dismissDialog();
    }

    @Override
    public void setParms(Map parms) {
        this.parms = parms;
    }

    @Override
    public void showFail(String message) {
        view.showFail(message);
        view.dismissDialog();

    }
}
