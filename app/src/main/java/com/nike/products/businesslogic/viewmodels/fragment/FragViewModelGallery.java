package com.nike.products.businesslogic.viewmodels.fragment;

import static android.content.ContentValues.TAG;
import static com.nike.products.utils.StaticData.getAPIKEY;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.nike.products.MyApplication;
import com.nike.products.R;
import com.nike.products.businesslogic.Network.ApiEndPoints;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;
import com.nike.products.models.pojo.Get_search_pojo;
import com.nike.products.utils.EnumVisibility;
import com.nike.products.utils.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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

                } else {

                    observerVisibility.set(EnumVisibility.NODATA);
                }

            } else {
                observerVisibility.set(EnumVisibility.NODATA);

            }

        }, throwable -> {

            observerVisibility.set(EnumVisibility.NODATA);

        }));

    }

    public void download(Get_search_pojo.Src model) {
        observerSnackBarString.set("Downloading...");


        getmCompositeDisposable().add(apiHelper.download_Image(model.getOriginal()).subscribeOn(mSchedulers.io()).observeOn(mSchedulers.ui()).subscribe(getSearchPojos -> {

            if (getSearchPojos != null) {
                getmCompositeDisposable().add(Single.fromCallable(() ->
                        writeResponseBodyToDisk(getSearchPojos)

                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(flag -> {
                    observerSnackBarString.set(flag ? "Downloaded Successfully" : "Downloaded Failed");

                }, throwable -> {
                    observerSnackBarString.set( "Downloaded Failed");

                }));


            }

        }, throwable -> {


        }));

    }

    long fileSize = 1;
    long fileSizeDownloaded = 0;

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            String end = body.contentType().toString();
            String exention = "png";

            if (end.contains("/") && end.length() > 0) {
                String[] ss1 = end.split("/");
                exention = ss1[1];

            }
            if (exention.equals("jpeg")) {
                exention = "jpg";
            }


            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Nike_Gallery");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File futureStudioIconFile = new File(dir, "nike_" + System.currentTimeMillis() + "." + exention);
            Logger.e("filePath", "" + body.contentType() + "  " + futureStudioIconFile.getAbsoluteFile());
            InputStream inputStream = null;
            OutputStream outputStream = null;


            try {
                byte[] fileReader = new byte[4096];

                fileSize = body.contentLength();

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Logger.e(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);


                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                Logger.e("IOException", e.toString());
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            Logger.e("IOExceptionp", e.toString());

            return false;
        }
    }


}
