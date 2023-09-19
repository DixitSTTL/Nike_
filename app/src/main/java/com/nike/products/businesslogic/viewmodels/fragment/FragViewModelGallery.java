package com.nike.products.businesslogic.viewmodels.fragment;

import static com.nike.products.utils.StaticData.getAPIKEY;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.nike.products.MyApplication;
import com.nike.products.businesslogic.Network.ApiEndPoints;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;
import com.nike.products.models.pojo.Get_search_pojo;
import com.nike.products.utils.EnumVisibility;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelGallery extends BaseViewModel {
    @Inject
    protected MyApplication myApplication;

    public ObservableArrayList<Get_search_pojo.Photo> observeImageList = new ObservableArrayList<>();
    public ObservableField<EnumVisibility> observerVisibility = new ObservableField<>(EnumVisibility.LOADING);

    @Inject
    public FragViewModelGallery(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    public void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put(ApiEndPoints.PARAMS_page, "1");
        map.put(ApiEndPoints.PARAMS_query, ApiEndPoints.VALUE_query);
        map.put(ApiEndPoints.PARAMS_per_page, ApiEndPoints.VALUE_per_page);

        Map<String, String> map2 = new HashMap<>();
        map2.put(ApiEndPoints.PARAMS_Authorization, getAPIKEY());
        observerVisibility.set(EnumVisibility.LOADING);
        getmCompositeDisposable().add(apiHelper.get_search_data(map, map2).subscribeOn(mSchedulers.io()).observeOn(mSchedulers.ui()).subscribe(getSearchPojos -> {

            if (getSearchPojos != null) {

                if (getSearchPojos.getPhotos() != null && getSearchPojos.getPhotos().size() > 0) {
                    observeImageList.clear();
                    observeImageList.addAll(getSearchPojos.getPhotos());
                    observerVisibility.set(EnumVisibility.VISIBLE);

                }else {

                    observerVisibility.set(EnumVisibility.NODATA);
                }

            }else {
                observerVisibility.set(EnumVisibility.NODATA);

            }

        }, throwable -> {

            observerVisibility.set(EnumVisibility.NODATA);

        }));

    }
}
