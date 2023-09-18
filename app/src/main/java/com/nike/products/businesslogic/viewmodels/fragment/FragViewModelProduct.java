package com.nike.products.businesslogic.viewmodels.fragment;

import androidx.databinding.ObservableBoolean;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.room.DatabaseHelper;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

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

        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoBookmark().getById(modelHome.getImage())
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(result -> {
                    if (result != null) {
                        observeBookmark.set(true);
                    } else {
                        observeBookmark.set(false);
                    }

                }, throwable -> {
                    observeBookmark.set(false);

                }));
    }

    public void bookmark(ModelHome modelHome) {
        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoBookmark().insertTask(modelHome).subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {
                    observeBookmark.set(true);
                    observerSnackBarString.set("Bookmarked Successfully");

                }, throwable -> {

                }));
    }

    public void unbookmark(ModelHome modelHome) {
        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoBookmark().deleteById(modelHome.getImage()).subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {
                    observeBookmark.set(false);
                    observerSnackBarString.set("Bookmarked Removed");

                }, throwable -> {

                }));
    }


    public void checkInCart(ModelCart modelCart) {
        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoCart().getById(modelCart.getImage()).subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(modelCart1 -> {

                    if (modelCart1 == null) {
                        addToCart(modelCart);
                    } else {
                        addQty(modelCart1);
                    }

                }, throwable -> {
                    addToCart(modelCart);

                }));
    }

    public void addToCart(ModelCart modelCart) {
        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoCart().insertCart(modelCart).subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {
                    observerSnackBarString.set("Added to cart");
                }, throwable -> {

                }));
    }

    public void addQty(ModelCart item) {

        if (item == null) {
            return;
        }
        int qty = item.getQty();
        qty++;
        item.setQty(qty);


        getmCompositeDisposable().add(DatabaseHelper.getInstance(context).daoCart().updateCart(item)
                .subscribeOn(mSchedulers.io())
                .observeOn(mSchedulers.ui())
                .subscribe(() -> {
                    observerSnackBarString.set("Added to cart");

                }, throwable -> {

                }));
    }
}
