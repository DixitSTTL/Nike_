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
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelCart;
import com.nike.products.databinding.FragmentCartBinding;
import com.nike.products.view.BaseFragment;

public class FragmentCart extends BaseFragment {

    private FragmentCartBinding mBinding;
    private FragViewModelCart mViewmodel;

    public FragmentCart() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_cart, container, false);
        initView();

        return mBinding.getRoot();
    }

    private void initView() {
        mViewmodel = new ViewModelProvider(mActivityMain).get(FragViewModelCart.class);
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralItemListener(generalItemClickListener);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewmodel.loadData();
    }

    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

            if (view.getId() == R.id.addBtn) {
                mViewmodel.addQty((ModelCart) item);
            } else if (view.getId() == R.id.removeBtn) {
                mViewmodel.removeQty((ModelCart) item);
            } else if (view.getId() == R.id.deleteBtn) {
                mViewmodel.deleteProduct((ModelCart) item);
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        mActivityMain.setAppTitle("Cart");
    }

}