package com.nike.products.view.fragment;

import static com.nike.products.utils.ConstantCodes.REQUEST_WRITE_STORAGE_PERMISSION_DOC;

import android.Manifest;
import android.os.Build;
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
import com.nike.products.models.pojo.Get_search_pojo;
import com.nike.products.utils.PermissionHelper;
import com.nike.products.view.BaseFragment;

public class FragmentGallery extends BaseFragment {

    FragmentGalleryBinding mBinding;
    FragViewModelGallery mViewmodel;

    Get_search_pojo.Src model;

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
        initView();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private void initView() {
        mViewmodel = new ViewModelProvider(mActivityMain).get(FragViewModelGallery.class);
        mViewmodel.loadData();
        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setMViewmodel(mViewmodel);
    }

    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {
            model = (Get_search_pojo.Src) item;
            checkPermission();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mActivityMain.setAppTitle("");
    }

    public void checkPermission() {
        String[] permission = new String[0];

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
        if (PermissionHelper.isPermissionWithGranted(permission, REQUEST_WRITE_STORAGE_PERMISSION_DOC, mActivity)) {

            downloadImage();
        }
    }

    private void downloadImage() {
        if (model != null) {
            mViewmodel.download(model);
        }


    }

}