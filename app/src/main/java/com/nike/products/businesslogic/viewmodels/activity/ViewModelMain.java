package com.nike.products.businesslogic.viewmodels.activity;

import android.util.Log;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ViewModelMain extends BaseViewModel {


    @Inject
    protected MyApplication mApplication;
    public ObservableBoolean observeVisibility = new ObservableBoolean(true);
    @Inject
    public ViewModelMain(MyApplication mApplication) {
        this.mApplication = mApplication;
    }
}
