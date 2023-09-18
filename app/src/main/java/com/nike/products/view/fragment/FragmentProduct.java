package com.nike.products.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralClickListener;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelProduct;
import com.nike.products.databinding.FragmentProductBinding;
import com.nike.products.view.BaseFragment;

public class FragmentProduct extends BaseFragment {

    ModelHome modelHome;
    FragmentProductBinding mBinding;
    FragViewModelProduct mViewmodel;

    public FragmentProduct() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_product, container, false);
        mViewmodel = new ViewModelProvider(mActivityMain).get(FragViewModelProduct.class);
        mViewmodel.checkBookmark(modelHome);


        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        animateArrows();
        GestureDetector mDetector = new GestureDetector(new MyGestureListener());
        mBinding.setModel(modelHome);
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralClickListener(generalClickListener);
        mBinding.setMDetector(mDetector);
    }

    private void animateArrows() {
        mBinding.down1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.alpha_anim));
        new Handler().postDelayed(() -> {
            mBinding.down2.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.alpha_anim));
        }, 300);
        new Handler().postDelayed(() -> {
            mBinding.down3.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.alpha_anim));
        }, 600);
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG", "onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            if (e1 == null || e2 == null) {
                return false;
            }

            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();
            Log.d("TAG", "onFling: " + diffY + "   " + velocityY);

            if (Math.abs(diffY) > Math.abs(diffX) && Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {

                    mBinding.imageView3.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.transcation_down));
                    ModelCart modelCart = new ModelCart(modelHome.getImage(), modelHome.getPrice(), modelHome.getName(), 1);
                    mViewmodel.checkInCart(modelCart);

                }
                return true;
            }
            return true;
        }
    }


    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.bookmarkBtn) {

                if (mViewmodel.observeBookmark.get()) {
                    mViewmodel.unbookmark(modelHome);
                } else {
                    mViewmodel.bookmark(modelHome);
                }
            } else {
                mBinding.imageView3.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.transcation_down));
                ModelCart modelCart = new ModelCart(modelHome.getImage(), modelHome.getPrice(), modelHome.getName(), 1);
                mViewmodel.checkInCart(modelCart);

            }


        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bagBtn) {
            mActivityMain.navigateToCart();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        mActivityMain.setAppTitle(modelHome.getName());
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void setData(ModelHome modelHome) {
        this.modelHome = modelHome;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.down1.clearAnimation();
        mBinding.down2.clearAnimation();
        mBinding.down3.clearAnimation();
    }
}