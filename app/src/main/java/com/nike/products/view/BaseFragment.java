package com.nike.products.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.nike.products.businesslogic.rx.SchedulerProvider;
import com.nike.products.view.activity.MainActivity;
import com.nike.products.MyApplication;
import com.nike.products.utils.preference.UtilsSharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseFragment extends Fragment {
    @Inject
    protected MyApplication mApplication;
    @Inject
    protected UtilsSharedPreferences preferences;
    @Inject
    protected SchedulerProvider mSchedulers;
    protected BaseActivity mActivity;
    protected MainActivity mActivityMain;
    protected Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity = (BaseActivity) getActivity();
        mActivityMain = (MainActivity) getActivity();
        mContext = BaseFragment.this.getContext();
    }

}
