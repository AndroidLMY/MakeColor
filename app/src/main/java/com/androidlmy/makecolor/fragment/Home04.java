package com.androidlmy.makecolor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.activity.LoginActivity;
import com.androidlmy.makecolor.utils.LoginUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Liming
 * Date: 2019/8/3 9:35
 * Created by Android Studio.
 */
public class Home04 extends Fragment {

    @BindView(R.id.tv_name)
    TextView tvName;

    public static Home04 newInstance() {
        return new Home04();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home04, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtils.clearLogin();
                LoginActivity.show(getContext());
            }
        });
    }
}
