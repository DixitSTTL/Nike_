package com.nike.products.utils;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelBookmark;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelHome;
import com.nike.products.view.activity.MainActivity;
import com.nike.products.view.adapter.AdapterBookmark;
import com.nike.products.view.adapter.AdapterHome;

import me.relex.circleindicator.CircleIndicator;


public class CustomBindingAdapter {


    @BindingAdapter(value = {"setSlider","setIndicator","setOnItemClickListener"})
    public static void setSlider(MyViewPager myViewPager, FragViewModelHome modelHome, CircleIndicator indicator, GeneralItemClickListener generalItemClickListener) {
        The_Slide_items_Pager_Adapter adapter = new The_Slide_items_Pager_Adapter(modelHome.observeBanner,myViewPager.getContext(),generalItemClickListener);
        myViewPager.setAdapter(adapter);
        indicator.setViewPager(myViewPager);
        modelHome.runViewpager(myViewPager);


    }


    @BindingAdapter(value = {"setHomeAdapter","setOnItemClickListener"})
    public static void setHomeAdapter(RecyclerView recyclerView, FragViewModelHome modelHome, GeneralItemClickListener generalItemClickListener) {

        AdapterHome adapterHome = new AdapterHome(modelHome.observeData,generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
    }


    @BindingAdapter(value = {"setAdapterBookmark","setOnItemClickListener"})
    public static void setAdapterBookmark(RecyclerView recyclerView, ObservableArrayList<ModelHome> list, GeneralItemClickListener generalItemClickListener) {

        Log.d("AdapterBookmark"," "+list.size());
        AdapterBookmark adapterHome = new AdapterBookmark(list,generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
    }


    @BindingAdapter(value = {"setImage"})
    public static void setImage(ImageView imageView, int src) {

        if (imageView!= null){
            imageView.setImageResource(src);
        }

    }




}
