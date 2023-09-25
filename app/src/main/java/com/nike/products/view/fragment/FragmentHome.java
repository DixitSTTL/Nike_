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

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralClickListener;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelGallery;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelHome;
import com.nike.products.databinding.FragmentHomeBinding;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.view.BaseFragment;


public class FragmentHome extends BaseFragment {

    FragmentHomeBinding mBinding;
    FragViewModelHome mViewModel;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_home, container, false);
        initView() ;


        return mBinding.getRoot();
    }
    private void initView() {
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelHome.class);
        mViewModel.loadData();
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);
        mBinding.setGeneralItemListener(generalItemClickListener);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.radioGroup.check(R.id.radio_1);
    }

    GeneralClickListener generalClickListener = view -> {


    };
    GeneralItemClickListener generalItemClickListener = (view, position, item) -> {
        mActivityMain.navigateToProduct((ModelHome) item);
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
        mActivityMain.setAppTitle("");
    }
}