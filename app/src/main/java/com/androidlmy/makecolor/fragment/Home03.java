package com.androidlmy.makecolor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.androidlmy.makecolor.R;

import butterknife.ButterKnife;

/**
 * author: Liming
 * Date: 2019/8/3 9:35
 * Created by Android Studio.
 */
public class Home03 extends Fragment  {

    public static Home03 newInstance() {
        return new Home03();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home03, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
