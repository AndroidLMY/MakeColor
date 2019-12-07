package com.androidlmy.makecolor.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.base.BaseActivity;
import com.androidlmy.makecolor.contract.RegisterContract;
import com.androidlmy.makecolor.presenter.RegisterPresenter;
import com.androidlmy.makecolor.utils.CountDownTimerUtils;
import com.androidlmy.makecolor.utils.PermissionUtils;
import com.androidlmy.makecolor.utils.title_utils.StatusBarUtil;
import com.bumptech.glide.Glide;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TResult;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class RegisterActivity extends TakePhotoActivity implements EasyPermissions.PermissionCallbacks, OnClickHeadView, RegisterContract.View {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.bt_regis)
    Button btRegis;
    @BindView(R.id.ic_head)
    CircleImageView icHead;
    private static final int PRC_PHOTO_PREVIEW = 10086;
    private Map<String, String> parms = new HashMap<>();
    private Context context;
    private Uri imageUri;//图片保存路径
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;//裁剪参数
    private CompressConfig compressConfig; //压缩参数
    String iconPath = "";
    private RegisterPresenter registerPresenter;
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
                    RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(iconPath));
                    MultipartBody.Part requestImgPart =
                            MultipartBody.Part.createFormData("multipartFile", new File(iconPath).getName(), fileRequestBody);
                    registerPresenter.setParms(parms, requestImgPart);
                    registerPresenter.getData();

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
                    Toast.makeText(context, object.getString("description"), Toast.LENGTH_SHORT).show();
                } catch (Exception e
                ) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setParms() {
        parms.clear();
        parms.put("password", etPassword.getText().toString());
        parms.put("name", etName.getText().toString());
        parms.put("phone", etPhone.getText().toString());

    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        context = getApplicationContext();
        initTitle();
        initTakePhoto();

        SMSSDK.registerEventHandler(eh);
        registerPresenter = new RegisterPresenter(this, this);


    }

    private void initTakePhoto() {
        ////获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        //        cropOptions = new CropOptions.Builder().setAspectX(16).setAspectY(16)
        //        .setWithOwnCrop(false).create();
        cropOptions =
                new CropOptions.Builder().setOutputX(1600).setAspectY(800).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig =
                new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, true); //设置为需要压缩
    }

    public void initTitle() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //        //设置状态栏透明
        //        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.white));

        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        title.setTitle("注册");
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

    @OnClick({R.id.tv_send_code, R.id.bt_regis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:
                SMSSDK.getVerificationCode("86", etPhone.getText().toString());
                break;
            case R.id.bt_regis:
                SMSSDK.submitVerificationCode("86", etPhone.getText().toString(), etCode.getText().toString());

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    @Override
    public void showDialog() {
    }

    @Override
    public void dismissDialog() {
    }

    @Override
    public void showSucceed(String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFail(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.ic_head)
    public void onViewClicked() {
        methodRequiresTwoPermission();
    }

    //请求码
    @AfterPermissionGranted(PRC_PHOTO_PREVIEW)
    private void methodRequiresTwoPermission() {
        String[] perms = {
                PermissionUtils.CAMERA,
                PermissionUtils.STORAGE,
        };
        if (EasyPermissions.hasPermissions(this, perms)) {
            imageUri = getImageCropUri();
            //从相册中选取图片并裁剪
            takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
            //从相册中选取不裁剪
            //takePhoto.onPickFromGallery();
            //表明已经授权，可以进行用户授予权限的操作
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "视频上传需要以下权限:", PRC_PHOTO_PREVIEW, perms);
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
        ArrayList iconPaths = result.getImages();
        //Google Glide库 用于加载图片资源
        Glide.with(this).load(iconPath).into(icHead);
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    /**
     * 拒绝权限前往设置中开启权限的回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            methodRequiresTwoPermission();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果
        Log.i("DDD", "onRequestPermissionsResult:" + requestCode);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //同意授权
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("DDD", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("DDD", "onPermissionsDenied:" + requestCode + ":" + perms.size());
        new AppSettingsDialog.Builder(this)
                .setTitle("提醒")
                .setRationale("此app需要这些权限才能正常使用")
                .build()
                .show();
    }
}
