package com.nike.products.businesslogic.viewmodels.fragment;

import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.room.DatabaseHelper;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class FragViewModelProduct extends BaseViewModel {
    @Inject
    MyApplication myApplication;
    public ObservableBoolean observeBookmark = new ObservableBoolean(false);

    @Inject
    public FragViewModelProduct(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    public void checkBookmark(ModelHome modelHome) {
        Log.d("subscribesubscribe","checkBookmark");

       Disposable disposable = DatabaseHelper.getInstance(context).dao().getById(modelHome.getImage())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(result -> {
                    Log.d("subscribesubscribe",""+result);
                    if (result != null) {
                        observeBookmark.set(true);
                    }else {
                        observeBookmark.set(false);
                    }

                }, throwable -> {
                    Log.d("subscribesubscribet",""+throwable.toString());
                    observeBookmark.set(false);

                });
    }

    public void bookmark(ModelHome modelHome) {
        Disposable disposable = DatabaseHelper.getInstance(context).dao().insertTask(modelHome).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(() -> {
                    observeBookmark.set(true);

                    Toast.makeText(context, "Bookmarked Successfully", Toast.LENGTH_SHORT).show();

                }, throwable -> {

                });
    }

    public void unbookmark(ModelHome modelHome) {
        Disposable disposable = DatabaseHelper.getInstance(context).dao().deleteById(modelHome.getImage()).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(() -> {
                    observeBookmark.set(false);
                    Toast.makeText(context, "Bookmarked Removed", Toast.LENGTH_SHORT).show();

                }, throwable -> {

                });
    }
}
