package com.nike.products.businesslogic.viewmodels.fragment;

import android.os.Handler;

import androidx.databinding.ObservableArrayList;

import com.nike.products.MyApplication;
import com.nike.products.R;
import com.nike.products.businesslogic.viewmodels.BaseViewModel;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.utils.MyViewPager;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelHome extends BaseViewModel {

    Timer timer;

    @Inject
    MyApplication myApplication;

    public ObservableArrayList<ModelHome> observeBanner = new ObservableArrayList<>();
    public ObservableArrayList<ModelHome> observeData = new ObservableArrayList<>();

    @Inject
    public FragViewModelHome(MyApplication application) {
        mApplication = application;

    }

    public void loadData() {
        observeBanner.add(new ModelHome(R.drawable.shoes_2, 25.99, "React Presto"));
        observeBanner.add(new ModelHome(R.drawable.shoes_8, 19.99, "Nike Downshifter 11"));
        observeBanner.add(new ModelHome(R.drawable.shoes_6, 29.99, "Nike Flex"));

        observeData.add(new ModelHome(R.drawable.shoes_1, 25.99, "React Presto"));
        observeData.add(new ModelHome(R.drawable.shoes_5, 20.99, "Air Max 97"));
        observeData.add(new ModelHome(R.drawable.shoes_4, 23.99, "React Presto"));
        observeData.add(new ModelHome(R.drawable.shoes_3, 23.99, "React Presto"));
        observeData.add(new ModelHome(R.drawable.shoes_6, 29.99, "Nike Flex"));
        observeData.add(new ModelHome(R.drawable.shoes_7, 25.99, "Nike Legend"));
        observeData.add(new ModelHome(R.drawable.shoes_8, 19.99, "Nike Downshifter 11"));
        observeData.add(new ModelHome(R.drawable.shoes_9, 30.99, "Nike Alpha"));
        observeData.add(new ModelHome(R.drawable.shoes_10, 23.99, "React Presto"));
        observeData.add(new ModelHome(R.drawable.shoes_11, 23.99, "React Presto"));
        observeData.add(new ModelHome(R.drawable.shoes_12, 29.99, "Nike Flex"));
        observeData.add(new ModelHome(R.drawable.shoes_13, 25.99, "Nike Legend"));
        observeData.add(new ModelHome(R.drawable.shoes_14, 19.99, "Nike Downshifter 11"));
        observeData.add(new ModelHome(R.drawable.shoes_15, 30.99, "Nike Alpha"));
    }

    public void runViewpager(MyViewPager myViewPager) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(myViewPager), 8000, 8000);

    }

    public class The_slide_timer extends TimerTask {
        MyViewPager myViewPager;

        public The_slide_timer(MyViewPager myViewPager) {
            this.myViewPager = myViewPager;

        }

        @Override
        public void run() {
            Handler mainHandler = new Handler(context.getMainLooper());

            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    if (myViewPager.getCurrentItem() < myViewPager.getAdapter().getCount() - 1) {
                        myViewPager.setCurrentItem(myViewPager.getCurrentItem() + 1);
                    } else {

                        myViewPager.setCurrentItem(0);
                    }
                } // This is your code
            };
            mainHandler.post(myRunnable);


        }
    }
}
