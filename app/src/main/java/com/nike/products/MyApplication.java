package com.nike.products;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;


import java.util.Calendar;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {


    @SuppressLint("ScheduleExactAlarm")
    @Override
    public void onCreate() {
        super.onCreate();


    }

}
