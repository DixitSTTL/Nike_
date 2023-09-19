package com.nike.products.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelGallery;
import com.nike.products.databinding.FragmentGalleryBinding;
import com.nike.products.view.BaseFragment;

public class FragmentGallery extends BaseFragment {

    FragmentGalleryBinding mBinding;
    FragViewModelGallery mViewmodel;

    public FragmentGallery() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_gallery, container, false);
        mViewmodel = new ViewModelProvider(mActivityMain).get(FragViewModelGallery.class);
        mViewmodel.loadData();
        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setMViewmodel(mViewmodel);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mActivityMain.setAppTitle("");
    }
}