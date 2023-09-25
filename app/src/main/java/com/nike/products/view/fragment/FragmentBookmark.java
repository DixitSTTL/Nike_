package com.nike.products.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelBookmark;
import com.nike.products.databinding.FragmentBookmarkBinding;
import com.nike.products.view.BaseFragment;

public class FragmentBookmark extends BaseFragment {

    FragmentBookmarkBinding mBinding;
    FragViewModelBookmark mViewmodel;

    public FragmentBookmark() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_bookmark, container, false);
        initView();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView() {
        mViewmodel = new ViewModelProvider(mActivityMain).get(FragViewModelBookmark.class);
        mViewmodel.loadData();
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralItemListener(generalItemClickListener);
    }

    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

            mViewmodel.removeFromBookmark((ModelHome) item);


        }
    };


    @Override
    public void onResume() {
        super.onResume();
        mActivityMain.setAppTitle("");

    }


}