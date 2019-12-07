package com.androidlmy.makecolor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlmy.headcustomview.HeadCustomView;
import com.androidlmy.makecolor.adapter.FriendAdapter;
import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.bean.FriendListBean;
import com.androidlmy.makecolor.contract.FriendContract;
import com.androidlmy.makecolor.presenter.FriendPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Liming
 * Date: 2019/8/3 9:35
 * Created by Android Studio.
 */
public class Home02 extends Fragment implements FriendContract.View {

    @BindView(R.id.title)
    HeadCustomView title;
    @BindView(R.id.iv_nodata)
    ImageView ivNodata;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.ll_nodata)
    LinearLayout llNodata;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;


    private List<FriendListBean.DataBean> friendBeanList = new ArrayList<>();

    private FriendAdapter adapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private FriendPresenter presenter;

    public static Home02 newInstance() {
        return new Home02();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home02, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new FriendPresenter(getActivity(), this);
        initList();
        intAdapter();
    }

    private void intAdapter() {
        if (adapter == null) {
            adapter = new FriendAdapter(getActivity(), friendBeanList);
            recyclerview.setLayoutManager(linearLayoutManager);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initList() {
        presenter.getData();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showSucceed(List<FriendListBean.DataBean> friendListBeans) {
        friendBeanList.clear();
        for (int i = 0; i < friendListBeans.size(); i++) {
            friendBeanList.add(friendListBeans.get(i));
        }
        intAdapter();
    }

    @Override
    public void showFail(String message) {

    }
}
