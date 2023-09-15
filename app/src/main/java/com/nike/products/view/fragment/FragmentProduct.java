package com.nike.products.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralClickListener;
import com.nike.products.businesslogic.room.DatabaseHelper;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelProduct;
import com.nike.products.databinding.FragmentProductBinding;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.view.BaseFragment;

import io.reactivex.disposables.Disposable;

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

        mBinding.setModel(modelHome);
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralClickListener(generalClickListener);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}