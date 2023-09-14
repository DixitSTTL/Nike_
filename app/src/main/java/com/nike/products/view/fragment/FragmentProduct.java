package com.nike.products.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralClickListener;
import com.nike.products.businesslogic.room.DatabaseHelper;
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

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (mViewmodel.observeBookmark.get()){
                mViewmodel.unbookmark(modelHome);
            }else {
                mViewmodel.bookmark(modelHome);
            }

        }
    };


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