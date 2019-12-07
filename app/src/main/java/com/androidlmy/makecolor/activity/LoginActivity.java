package com.androidlmy.makecolor.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.base.BaseActivity;
import com.androidlmy.makecolor.bean.LogBean;
import com.androidlmy.makecolor.bean.litepal.LoginLitepal;
import com.androidlmy.makecolor.contract.LoginContract;
import com.androidlmy.makecolor.presenter.LoginPresenter;
import com.androidlmy.makecolor.utils.title_utils.StatusBarUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_clean)
    ImageView ivClean;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_swich_passwrod)
    ImageView ivSwichPasswrod;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_find_pw)
    TextView tvFindPw;
    private LoginPresenter loginPresenter;
    private boolean isShowPassword = false;

    public static void show(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        loginPresenter = new LoginPresenter(this, this);
    }


    private void setLoginParm() {
        parms.clear();
        parms.put("phone", etPhone.getText().toString());
        parms.put("password", etPassword.getText().toString());
    }

    @Override
    public void showDialog() {
        showLoadDialog();
    }

    @Override
    public void dismissDialog() {
        hideLoadDialog();
    }

    @Override
    public void showSucceed(LogBean logBean) {
        LoginLitepal loginLitepal = new LoginLitepal(logBean.getData().getUser_id(), logBean.getData().getName(), logBean.getData().getPhone(), logBean.getData().getToken(), logBean.getData().getHeadurl());
        loginLitepal.save();
        Toast.makeText(context, logBean.getMessage(), Toast.LENGTH_SHORT).show();
        LiveEventBus.get("refresh_login").post(logBean.getData().getToken());
        finish();
    }

    @Override
    public void showFail(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.iv_clean, R.id.iv_swich_passwrod, R.id.bt_login, R.id.tv_register, R.id.tv_find_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean:
                etPhone.setText("");
                break;
            case R.id.iv_swich_passwrod:
                if (isShowPassword) {
                    ivSwichPasswrod.setImageResource(R.mipmap.show_psw_press);
                    isShowPassword = false;
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivSwichPasswrod.setImageResource(R.mipmap.show_psw);
                    isShowPassword = true;
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.bt_login:
                setLoginParm();
                loginPresenter.setParms(parms);
                loginPresenter.getData();
                break;
            case R.id.tv_register:
                RegisterActivity.show(this);
                break;
            case R.id.tv_find_pw:
                FindPassActivity.show(this);
                break;
        }
    }
}
