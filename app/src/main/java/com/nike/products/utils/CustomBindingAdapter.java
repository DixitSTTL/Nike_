package com.nike.products.utils;

import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.rx.AppSchedulerProvider;
import com.nike.products.businesslogic.rx.SchedulerProvider;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelHome;
import com.nike.products.models.pojo.Get_search_pojo;
import com.nike.products.view.adapter.AdapterBookmark;
import com.nike.products.view.adapter.AdapterCart;
import com.nike.products.view.adapter.AdapterGallery;
import com.nike.products.view.adapter.AdapterHome;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator;


public class CustomBindingAdapter {
    SchedulerProvider schedulerProvider = new AppSchedulerProvider();

    @BindingAdapter(value = {"setSlider", "setIndicator", "setOnItemClickListener"})
    public static void setSlider(MyViewPager myViewPager, FragViewModelHome modelHome, CircleIndicator indicator, GeneralItemClickListener generalItemClickListener) {
        The_Slide_items_Pager_Adapter adapter = new The_Slide_items_Pager_Adapter(modelHome.observeBanner, myViewPager.getContext(), generalItemClickListener);
        myViewPager.setAdapter(adapter);
        indicator.setViewPager(myViewPager);
        modelHome.runViewpager(myViewPager);
    }

    @BindingAdapter(value = {"setHomeAdapter", "setOnItemClickListener"})
    public static void setHomeAdapter(RecyclerView recyclerView, FragViewModelHome modelHome, GeneralItemClickListener generalItemClickListener) {
        AdapterHome adapterHome = new AdapterHome(modelHome.observeData, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
    }

    @BindingAdapter(value = {"setAdapterBookmark", "setOnItemClickListener"})
    public static void setAdapterBookmark(RecyclerView recyclerView, ObservableArrayList<ModelHome> list, GeneralItemClickListener generalItemClickListener) {
        AdapterBookmark adapterHome = new AdapterBookmark(list, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @BindingAdapter(value = {"setAdapterCart", "setOnItemClickListener"})
    public static void setAdapterCart(RecyclerView recyclerView, ObservableArrayList<ModelCart> list, GeneralItemClickListener generalItemClickListener) {
        AdapterCart adapterHome = new AdapterCart(list, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @BindingAdapter(value = {"setAdapterGallery", "setOnItemClickListener"})
    public static void setAdapterGallery(RecyclerView recyclerView, ObservableArrayList<Get_search_pojo.Photo> list, GeneralItemClickListener generalItemClickListener) {
        AdapterGallery adapterHome = new AdapterGallery(list, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @BindingAdapter(value = {"setImage"})
    public static void setImage(ImageView imageView, int src) {
        if (imageView != null) {
            imageView.setImageResource(src);
        }
    }

    @BindingAdapter(value = {"setGlideImage"})
    public static void setGlideImage(ImageView imageView, String url) {
        if (imageView != null) {

    /*         Glide.with(imageView.getContext()).load(url)
                    .placeholder(R.drawable.ic_nike_logo_white)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(imageView);*/

    /*            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_nike_logo_white)
                    .into(imageView);*/

            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(Single.fromCallable(() -> Glide.with(imageView.getContext()).asBitmap().load(url).placeholder(R.drawable.ic_nike_logo_white).submit().get()


            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(resource -> {
                int originalWidth = resource.getWidth();
                int originalHeight = resource.getHeight();
                int targetWidth = imageView.getWidth(); // Get the ImageView's width
                int targetHeight = (int) ((float) targetWidth / originalWidth * originalHeight);
                // Update the ImageView's layout params with the calculated height
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = targetHeight;
                imageView.setLayoutParams(layoutParams);
                // Set the loaded image to the ImageView
                imageView.setImageBitmap(resource);

            }, throwable -> {

                imageView.setImageResource(R.drawable.ic_nike_logo_white);


            }));
            ;


        }
    }

    @BindingAdapter(value = {"setImage", "setImageWithGesture"})
    public static void setImageWithGesture(ImageView imageView, int src, GestureDetector mDetector) {
        if (imageView != null) {
            imageView.setImageResource(src);
            View.OnTouchListener touchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return mDetector.onTouchEvent(motionEvent);
                }
            };
            imageView.setOnTouchListener(touchListener);

        }
    }

    @BindingAdapter(value = {"showSnackBarString"}, requireAll = false)
    public static void showSnackBar(View viewLayout, ObservableField<String> snackMessageString) {
        String message = "";
        if (snackMessageString != null && !TextUtils.isEmpty(snackMessageString.get())) {
            message = snackMessageString.get().trim();
            snackMessageString.set("");
        }
        if (!TextUtils.isEmpty(message)) {
            Utils.showSnackBar(viewLayout, message);
        }
    }


}
