package com.androidlmy.makecolor.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.bean.UpDataBean;
import com.androidlmy.makecolor.customview.update_app.UpDataDialog;
import com.androidlmy.makecolor.customview.update_app.UpDataProgressDialog;
import com.androidlmy.makecolor.net.okhttp.DownloadListener;
import com.androidlmy.makecolor.net.okhttp.DownloadUtil;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * @功能:下载app更新工具类
 * @Creat 2019/07/05 15:47
 * @User Lmy
 * @By Android Studio
 */
public class AppUpDataUtils {
    public static AppUpDataUtils downloadUtils;
    private AppCompatActivity context;
    private UpDataBean.DataBean upBean;
    public static String filename = "updata_app.apk";
    private NotificationManager notificationManager;
    private Notification notification; //下载通知进度提示
    private NotificationCompat.Builder builder;

    private Call<ResponseBody> mCall;


    public static AppUpDataUtils getInstance() {
        if (downloadUtils == null) {
            downloadUtils = new AppUpDataUtils();
        }
        return downloadUtils;
    }

    public void initData(AppCompatActivity context, UpDataBean.DataBean bean, boolean isShowToast) {
        this.context = context;
        this.upBean = bean;
        File f =
                new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download" + File.separator + AppUpDataUtils.filename);
        if (f.exists()) {
            //如果文件存在则删除文件
            f.delete();
        }
        int localVersion = getLocalVersion(context);
        int i = Integer.parseInt(bean.getVersionNo());
        if (i > localVersion) {
            showUpDataDialog(context);
        } else {
            if (isShowToast)
                ToastUtil.show(context, "已经是最新版本");
        }
    }

    public void showUpDataDialog(Activity context) {
        UpDataDialog.getInstance(context).setTitle("升级到" + upBean.getVersion() + "版本")
                .setisLong(false)
                .setMessage(upBean.getContent())
                .setDialogCancelable(false)
                .setPositiveButton("立即更新", new UpDataDialog.OnMyDialogButtonClickListener() {
                    @Override
                    public void onClick() {
                        downLoadUpdate(upBean.getFilepath());
                    }
                })
                .setNegativeButton("下次再说", new UpDataDialog.OnMyDialogButtonClickListener() {
                    @Override
                    public void onClick() {
                    }
                })
                .show();
    }

    public void downLoadUpdate(String url) {
        UpDataProgressDialog dataProgressDialog = UpDataProgressDialog.getInstance(context);
        dataProgressDialog.setTitle("升级到" + upBean.getVersion() + "版本");
        dataProgressDialog.setDialogCancelable(false);//设置禁止取下载进度条
        dataProgressDialog.show();
//        initNotification();
        DownloadUtil mDownloadUtil = new DownloadUtil();
        mDownloadUtil.downloadFile(url, new DownloadListener() {
            @Override
            public void onStart() {
                LogUtil.e("开始下载..........");
            }

            @Override
            public void onProgress(int currentLength) {
                LogUtil.e("下载进度:" + ">>>>>>>>>>" + currentLength);
                dataProgressDialog.setProgress((int) (currentLength));
            }

            @Override
            public void onFinish(String localPath) {
                LogUtil.e("下载结束..........");
                dataProgressDialog.dismiss();
                installAPK(context, new File(localPath));
            }

            @Override
            public void onFailure(String erroInfo) {
                LogUtil.e(erroInfo + "..........");

            }
        });
    }

    public void installAPK(Context activity, File filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 版本大于 N ，开始使用 fileProvider 进行安装
            Uri apkUri = FileProvider.getUriForFile(activity, "com.androidlmy.stockcontrol.fileprovider", filePath);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            // 正常进行安装
            intent.setDataAndType(Uri.fromFile(filePath), "application/vnd.android.package-archive");
        }
        activity.startActivity(intent);
    }
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static String getVersionName(Context ctx) {
        String localVersion = null;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName; // 版本名

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    //初始化通知
    private void initNotification() {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launchers)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launchers)) //设置通知的大图标
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        notification = builder.build();//构建通知对象
    }
}
