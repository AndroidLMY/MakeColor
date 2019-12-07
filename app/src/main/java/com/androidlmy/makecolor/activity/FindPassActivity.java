package com.androidlmy.makecolor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.base.BaseActivity;
import com.androidlmy.makecolor.contract.AlterPassContract;
import com.androidlmy.makecolor.presenter.AlterPassPresenter;
import com.androidlmy.makecolor.utils.CountDownTimerUtils;
import com.androidlmy.makecolor.utils.ToastUtil;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class FindPassActivity extends BaseActivity implements OnClickHeadView, AlterPassContract.View {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_sure_password)
    EditText etSurePassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.bt_sure)
    Button btSure;
    @BindView(R.id.et_phone)
    EditText etPhone;
    private AlterPassPresenter alterPassPresenter;
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            mHandler.sendMessage(msg);
        }
    };
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.d("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("--------result" + event);
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    setParms();
                    alterPassPresenter.setParms(parms);
                    alterPassPresenter.getData();

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //已经验证
                    Toast.makeText(context, "验证码已经发送", Toast.LENGTH_SHORT).show();
//                    CountDownTimerUtils.getInstance().startCountdown(tvSendCode, 60000, 1000, R.drawable.shape_edittext_rounded_rectangle, R.drawable.shape_edittext_rounded_rectangle);
                    CountDownTimerUtils.getInstance().startCountdown(tvSendCode, 60000, 1000, R.drawable.button_shap_f_bluet, R.drawable.getcode_bg);

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
//                    Toast.makeText(context, "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    String stri = ((Throwable) data).getMessage();
                    JSONObject object = new JSONObject(stri);
                    Toast.makeText(context, object.getString("detail"), Toast.LENGTH_SHORT).show();
                } catch (Exception e
                ) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setParms() {
        parms.clear();
        parms.put("phone", etPhone.getText().toString());
        parms.put("password", etPassword.getText().toString());

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, FindPassActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        ButterKnife.bind(this);
        alterPassPresenter = new AlterPassPresenter(this, this);
        initTitle();
        SMSSDK.registerEventHandler(eh);

    }

    @Override
    public void initTitle() {
        title.setTitle("找回密码");
        title.setTitleTextSize(16);
        title.setBackImg(R.drawable.ic_back_black);
        title.setClickCallBack(this);
    }

    @Override
    public void onBackClick() {

        finish();
    }

    @Override
    public void onBackTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @OnClick({R.id.tv_send_code, R.id.bt_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:
                SMSSDK.getVerificationCode("86", etPhone.getText().toString());
                break;
            case R.id.bt_sure:
                if (!etPassword.getText().toString().equals(etSurePassword.getText().toString())) {
                    ToastUtil.show(context, "两次输入密码不一致");
                    return;
                }
                if (etPhone.getText().toString().length() < 11) {
                    ToastUtil.show(context, "手机号输入错误");
                }
                SMSSDK.submitVerificationCode("86", etPhone.getText().toString(), etCode.getText().toString());

                break;
        }
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
    public void showSucceed(String message) {
        ToastUtil.show(context, message);
    }

    @Override
    public void showFail(String message) {
        ToastUtil.show(context, message);
    }
}
