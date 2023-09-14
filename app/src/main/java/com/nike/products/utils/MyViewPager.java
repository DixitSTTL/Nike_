package com.nike.products.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;


import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class MyViewPager extends ViewPager {
    private boolean isPagingEnabled = true;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMyScroller();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Disable touch event interception
        return isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Disable touch events
        return isPagingEnabled && super.onTouchEvent(event);
    }


    private void setMyScroller() {
        try {
            Class<?> viewPagerClass = ViewPager.class;
            Field scrollerField = viewPagerClass.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            scrollerField.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MyScroller extends Scroller {
        private static final int DURATION = 500; // Adjust the duration as per your preference

        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, DURATION); // Use the custom duration
        }
    }
}