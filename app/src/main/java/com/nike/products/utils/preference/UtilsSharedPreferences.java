package com.nike.products.utils.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilsSharedPreferences {

    Context context;
    SharedPreferences sharedPreferences;

    public UtilsSharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    public String getString(int key) {
        return sharedPreferences.getString(context.getResources().getString(key), "");
    }

    public String getStringDefault(int key,String defaultStr) {
        return sharedPreferences.getString(context.getResources().getString(key), defaultStr);
    }

    public void setString(int key, String vale) {

        sharedPreferences.edit().putString(context.getResources().getString(key), vale).apply();
    }
}
