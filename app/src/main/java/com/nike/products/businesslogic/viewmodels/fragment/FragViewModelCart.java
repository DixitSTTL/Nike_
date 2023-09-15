package com.nike.products.businesslogic.viewmodels.fragment;

import androidx.databinding.ObservableArrayList;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.room.DatabaseHelper;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.Disposable;

@HiltViewModel

public class FragViewModelCart extends BaseViewModel {


    @Inject
    MyApplication myApplication;
    public ObservableArrayList<ModelCart> observeCart = new ObservableArrayList<>();

    @Inject
    public FragViewModelCart(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    public void loadData() {

        Disposable disposable = DatabaseHelper.getInstance(context).daoCart().getAllCartProducts()
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(modelCarts -> {
                    if (modelCarts != null) {
                        observeCart.clear();
                        observeCart.addAll(modelCarts);
                    }

                }, throwable -> {

                });
    }


    public void addQty(ModelCart item) {

        if (item == null) {
            return;
        }
        int qty = item.getQty();
        qty++;
        item.setQty(qty);


        Disposable disposable = DatabaseHelper.getInstance(context).daoCart().updateCart(item)
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {

                }, throwable -> {

                });
    }

    public void removeQty(ModelCart item) {
        if (item == null || item.getQty() <= 1) {
            return;
        }
        int qty = item.getQty();
        qty--;
        item.setQty(qty);
        Disposable disposable = DatabaseHelper.getInstance(context).daoCart().updateCart(item)
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {

                }, throwable -> {

                });
    }

    public void deleteProduct(ModelCart item) {
        Disposable disposable = DatabaseHelper.getInstance(context).daoCart().deleteCart(item)
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {
                    observerSnackBarString.set("Removed Successfully");

                }, throwable -> {

                });

    }
}
