package com.nike.products.businesslogic.viewmodels;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.rx.SchedulerProvider;
import com.nike.products.utils.preference.UtilsSharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class BaseViewModel extends ViewModel {

    @Inject
    protected MyApplication mApplication;

    @Inject
    protected Context context;
    @Inject
    protected UtilsSharedPreferences preferences;

    @Inject
    protected SchedulerProvider mSchedulers;

    public ObservableField<String> observerSnackBarString = new ObservableField<>("");

    @Inject
    public BaseViewModel() {
    }


    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }


}
