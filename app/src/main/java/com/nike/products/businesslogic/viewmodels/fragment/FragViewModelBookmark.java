package com.nike.products.businesslogic.viewmodels.fragment;

import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.room.DatabaseHelper;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;
import com.nike.products.businesslogic.room.entity.ModelHome;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class FragViewModelBookmark extends BaseViewModel {

    @Inject
    MyApplication myApplication;
    public ObservableArrayList<ModelHome> observeBookmarks = new ObservableArrayList<>();

    @Inject
    public FragViewModelBookmark(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    public void loadData() {

        getmCompositeDisposable().add(DatabaseHelper.getInstance(myApplication.getApplicationContext()).daoBookmark().getAllBookmark()
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(modelHomes -> {

                    Log.d("modelHomes", "" + modelHomes);

                    if (modelHomes != null) {
                        observeBookmarks.clear();
                        observeBookmarks.addAll(modelHomes);
                    }

                }));


    }


    public void removeFromBookmark(ModelHome item) {
        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoBookmark().deleteTask(item)
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {

                    observerSnackBarString.set("Bookmarked Removed");

                }, throwable -> {

                }));
    }
}
