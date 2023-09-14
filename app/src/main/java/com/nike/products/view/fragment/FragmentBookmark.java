package com.nike.products.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.DatabaseHelper;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.businesslogic.viewmodels.fragment.FragViewModelBookmark;
import com.nike.products.databinding.FragmentBookmarkBinding;
import com.nike.products.view.BaseFragment;

import io.reactivex.disposables.Disposable;

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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_bookmark, container, false);
        mViewmodel = new ViewModelProvider(mActivityMain).get(FragViewModelBookmark.class);
        mViewmodel.loadData();
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralItemListener(generalItemClickListener);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }
    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

            Disposable disposable = DatabaseHelper.getInstance(mContext).dao().deleteTask((ModelHome) item)
                    .subscribeOn(mSchedulers.io())
                    .observeOn(mSchedulers.ui())
                    .subscribe(() -> {

                        Toast.makeText(mContext, "Bookmarked Removed", Toast.LENGTH_SHORT).show();

                    }, throwable -> {

                    });

        }
    };


    @Override
    public void onResume() {
        super.onResume();
        mActivityMain.setAppTitle("");

    }


}