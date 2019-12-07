package com.androidlmy.makecolor;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.androidlmy.makecolor.utils.CrashHandler;
import com.androidlmy.makecolor.utils.LogUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import io.rong.imkit.RongIM;

/**
 * @功能:
 * @Creat 2019/10/23 8:56
 * @User Lmy
 * @Compony zaituvideo
 */
public class app extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initLogInterceptor();//Log配置
        initLitePal();//初始化LitePal
        initException();//全局异常捕获
        initLiveEventBus();//初始化LiveEventBus
        initBugLy();//初始化腾讯Bugly
        RongIM.init(this);
    }

    /**
     * 初始化腾讯Bugly
     */
    private void initBugLy() {
        // 获取当前包名
        String packageName = mContext.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(mContext, "5550796c69", true, strategy);
    }

    /**
     * 全局异常捕获 捕获到异常重启app
     */
    private void initException() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(mContext));

    }

    /**
     * 初始化LitePal
     */
    private void initLitePal() {
        LitePal.initialize(this);
    }

    /**
     * 配置全局log打印的配置
     */
    private void initLogInterceptor() {
        LogUtil.isPrintLog = true;
        LogUtil.TGA = "com.androidlmy.stockcontrol";
    }

    /**
     * 初始化LiveEventBus
     * 1、supportBroadcast配置支持跨进程、跨APP通信
     * 2、配置LifecycleObserver（如Activity）接收消息的模式（默认值true）：
     * true：整个生命周期（从onCreate到onDestroy）都可以实时收到消息
     * false：激活状态（Started）可以实时收到消息，非激活状态（Stoped）无法实时收到消息，需等到Activity重新变成激活状
     * 态，方可收到消息
     * 3、autoClear
     * 配置在没有Observer关联的时候是否自动清除LiveEvent以释放内存（默认值false）
     */
    private void initLiveEventBus() {
        LiveEventBus.
                config().
                supportBroadcast(this).
                lifecycleObserverAlwaysActive(true).
                autoClear(false);
    }

    /**
     * @return 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
