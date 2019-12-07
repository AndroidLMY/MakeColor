package com.androidlmy.makecolor.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.androidlmy.makecolor.adapter.FraTabAdapter;
import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.base.BaseActivity;
import com.androidlmy.makecolor.bean.UpDataBean;
import com.androidlmy.makecolor.bean.litepal.LoginLitepal;
import com.androidlmy.makecolor.contract.UpDataContract;
import com.androidlmy.makecolor.customview.AlertDialog;
import com.androidlmy.makecolor.customview.CustomViewPager;
import com.androidlmy.makecolor.fragment.Home01;
import com.androidlmy.makecolor.fragment.Home02;
import com.androidlmy.makecolor.fragment.Home03;
import com.androidlmy.makecolor.fragment.Home04;
import com.androidlmy.makecolor.presenter.UpDataPresenter;
import com.androidlmy.makecolor.utils.AppUpDataUtils;
import com.androidlmy.makecolor.utils.LogUtil;
import com.androidlmy.makecolor.utils.LoginUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

public class MainActivity extends BaseActivity implements UpDataContract.View {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    private UpDataPresenter upDataPresenter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MenuItem menuItem;
    private LoginLitepal loginLitepal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        upDataPresenter = new UpDataPresenter(this, this);
        upDataPresenter.setParms(parms);
        upDataPresenter.getData();
        initStatusBar();
        inAdapter();
        initLiveEventBus();
        isLogin();
    }

    private void isLogin() {
        if (!LoginUtils.isLogin()) {
            AlertDialog.getInstance(MainActivity.this).setTitle("提示")
                    .setisLong(false)
                    .setMessage("暂未登录,是否前往登录?")
                    .setDialogCancelable(false)
                    .setPositiveButton("确认", new AlertDialog.OnMyDialogButtonClickListener() {
                        @Override
                        public void onClick() {
                            LoginActivity.show(MainActivity.this);
                        }
                    })
                    .setNegativeButton("取消", new AlertDialog.OnMyDialogButtonClickListener() {
                        @Override
                        public void onClick() {
                        }
                    })
                    .show();
        } else {
            connect(loginLitepal.getToken()
                    , loginLitepal.getUser_id(), loginLitepal.getUser_name(), loginLitepal.getHeadurl());//账号1
        }
    }

    private void inAdapter() {
        viewPager.setScanScroll(true);//禁止滑动
        fragmentList.add(Home01.newInstance());
        fragmentList.add(Home02.newInstance());
        fragmentList.add(Home03.newInstance());
        fragmentList.add(Home04.newInstance());
        FraTabAdapter fraAdapter = new FraTabAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fraAdapter);
        //设置viewpager缓存数量
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuItem = item;
                int i = item.getItemId();
                if (i == R.id.navigation_msg) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (i == R.id.navigation_find) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (i == R.id.navigation_video) {
                    viewPager.setCurrentItem(2);
                    return true;
                } else if (i == R.id.navigation_me) {
                    viewPager.setCurrentItem(3);
                    return true;
                }
                return false;
            }


        });
    }

    @Override
    public void initStatusBar() {

        loginLitepal = LoginUtils.getLoginBean();

    }


    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {#init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token 从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token, final String id, final String name, final String headurl) {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtil.d("onTokenIncorrect: ---Token错误");
                    Toast.makeText(MainActivity.this, "融云Token错误", Toast.LENGTH_SHORT).show();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtil.d("连接融云成功--" + userid);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(id, name, Uri.parse(headurl)));
                    //为了显示陌生人的头像，携带到消息中
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    RongIM.getInstance().enableNewComingMessageIcon(true);//显示新消息提醒
                    RongIM.getInstance().enableUnreadMessageIcon(true);//显示未读消息数目
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtil.d("onTokenIncorrect: ---" + "链接融云服务器失败，错误码：" + errorCode);
                    Toast.makeText(MainActivity.this, "链接融云服务器失败，错误码：" + errorCode, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void initLiveEventBus() {
        LiveEventBus.get("refresh_login", String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                loginLitepal = LoginUtils.getLoginBean();
                isLogin();
            }
        });
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
    public void showUpDataSucceed(UpDataBean.DataBean dataBean) {
        AppUpDataUtils.getInstance().initData(MainActivity.this, dataBean, false);
    }

    @Override
    public void showFail(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
