package com.androidlmy.makecolor.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.headcustomview.OnClickHeadView;
import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.base.BaseActivity;
import com.androidlmy.makecolor.utils.LogUtil;
import com.androidlmy.makecolor.utils.LoginUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.callkit.RongCallProxy;
import io.rong.calllib.IRongCallListener;
import io.rong.calllib.RongCallClient;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


public class ConversationActivity extends BaseActivity implements OnClickHeadView {
    @BindView(R.id.title)
    HeadCustomView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);
        initTitle();
    }

    @Override
    public void initTitle() {
        super.initTitle();
        title.setTitle(getIntent().getData().getQueryParameter("title").toString());
        title.setTitleTextSize(16);
        title.setBackImg(R.drawable.ic_back_black);
        title.setClickCallBack(this);
        RongIM.connect(LoginUtils.getLoginBean().getToken(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                RongCallProxy.getInstance().setAppCallListener(new IRongCallListener() {
                    @Override
                    public void onCallOutgoing(final RongCallSession rongCallSession, SurfaceView surfaceView) {
                        //拨出通话
                        LogUtil.d("拨出通话");
                    }

                    @Override
                    public void onCallConnected(final RongCallSession rongCallSession, SurfaceView surfaceView) {
                        //已建立通话。
                        LogUtil.d("已建立通话");
//                        new Thread() {
//                            @Override
//                            public void run() {
//                                super.run();
//                                try {
//                                    Thread.sleep(6000);//休眠6秒
//                                    RongCallClient.getInstance().hangUpCall(rongCallSession.getCallId());
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }.start();
                    }

                    @Override
                    public void onCallDisconnected(RongCallSession rongCallSession, RongCallCommon.CallDisconnectedReason callDisconnectedReason) {
                        //通话结束。
                        LogUtil.d("开始时间" + rongCallSession.getStartTime() + "\n结束时间" + rongCallSession.getEndTime() + "\nActivity时间" + rongCallSession.getActiveTime());
                    }

                    @Override
                    public void onRemoteUserRinging(String s) {
                        //被呼叫端正在响铃
                        LogUtil.d("被呼叫端正在响铃");

                    }

                    @Override
                    public void onRemoteUserJoined(String s, RongCallCommon.CallMediaType callMediaType, int i, SurfaceView surfaceView) {
                        LogUtil.d("onRemoteUserJoined");

                    }

                    @Override
                    public void onRemoteUserInvited(String s, RongCallCommon.CallMediaType callMediaType) {
                        LogUtil.d("onRemoteUserInvited");
                    }

                    @Override
                    public void onRemoteUserLeft(String s, RongCallCommon.CallDisconnectedReason callDisconnectedReason) {
                        LogUtil.d("onRemoteUserLeft");

                    }

                    @Override
                    public void onMediaTypeChanged(String s, RongCallCommon.CallMediaType callMediaType, SurfaceView surfaceView) {
                        LogUtil.d("onMediaTypeChanged");

                    }

                    @Override
                    public void onError(RongCallCommon.CallErrorCode callErrorCode) {
                        //通话过程中，发生异常。
                        LogUtil.d("通话过程中，发生异常。");

                    }

                    @Override
                    public void onRemoteCameraDisabled(String s, boolean b) {
                        LogUtil.d("onRemoteCameraDisabled");

                    }

                    @Override
                    public void onRemoteMicrophoneDisabled(String s, boolean b) {
                        LogUtil.d("onRemoteMicrophoneDisabled");

                    }

                    @Override
                    public void onNetworkReceiveLost(String s, int i) {
                        LogUtil.d("onNetworkReceiveLost");

                    }

                    @Override
                    public void onNetworkSendLost(int i, int i1) {
                        LogUtil.d("onNetworkSendLost");

                    }

                    @Override
                    public void onFirstRemoteVideoFrame(String s, int i, int i1) {
                        LogUtil.d("onFirstRemoteVideoFrame");

                    }

                    @Override
                    public void onAudioLevelSend(String s) {
                        LogUtil.d("onAudioLevelSend");

                    }

                    @Override
                    public void onAudioLevelReceive(HashMap<String, String> hashMap) {
                        LogUtil.d("onAudioLevelReceive");

                    }

                });
//                RongIM.getInstance().setSendMessageListener(new SendMessageListener(ConversationActivity.this));
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });


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
}
