package com.nike.products.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.rx.SchedulerProvider;
import com.nike.products.utils.preference.UtilsSharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {
    @Inject
    protected Context mContext;
    @Inject
    protected UtilsSharedPreferences preferences;
    @Inject
    protected MyApplication myApplication;
    @Inject
    protected SchedulerProvider mSchedulers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
