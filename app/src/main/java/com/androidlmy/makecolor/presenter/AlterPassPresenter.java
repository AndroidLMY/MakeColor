package com.androidlmy.makecolor.presenter;

import android.content.Context;

import com.androidlmy.makecolor.contract.AlterPassContract;
import com.androidlmy.makecolor.model.AlterPassModel;

import java.util.Map;

/**
 * @功能:
 * @Creat 2019/11/22 14:28
 * @User Lmy
 * @Compony zaituvideo
 */
public class AlterPassPresenter implements AlterPassContract.Presenter {
    private Context context;
    private AlterPassContract.View view;
    private AlterPassModel model;
    private Map parms;

    public AlterPassPresenter(Context context, AlterPassContract.View view) {
        this.context = context;
        this.view = view;
        model = new AlterPassModel();
    }

    @Override
    public void getData() {
        view.showDialog();
        model.getData(context, this, parms);

    }

    @Override
    public void showSucceed(String message) {
        view.dismissDialog();
        view.showSucceed(message);
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
