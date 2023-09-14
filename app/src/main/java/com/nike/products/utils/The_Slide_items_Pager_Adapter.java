package com.nike.products.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.entity.ModelHome;

import java.util.List;


public class The_Slide_items_Pager_Adapter extends PagerAdapter {

    private Context Mcontext;
    private List<ModelHome> product_banner_list;
    GeneralItemClickListener generalItemClickListener;
    public The_Slide_items_Pager_Adapter(List<ModelHome> product_banner_list, Context Mcontext, GeneralItemClickListener generalItemClickListener) {
        this.product_banner_list = product_banner_list;
        this.Mcontext = Mcontext;
        this.generalItemClickListener = generalItemClickListener;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.holder_banner, container, false);
        ImageView image = sliderLayout.findViewById(R.id.imageView);
        image.setImageResource(product_banner_list.get(position).getImage());
        sliderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generalItemClickListener.onItemClick(view,position,(ModelHome) product_banner_list.get(position));
            }
        });
        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return product_banner_list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

}