package com.nike.products.view.activity;

import static androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.splashscreen.SplashScreen;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nike.products.R;
import com.nike.products.businesslogic.viewmodels.activity.ViewModelMain;
import com.nike.products.databinding.ActivityMainBinding;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.view.ActivityBase;
import com.nike.products.view.fragment.FragmentBookmark;
import com.nike.products.view.fragment.FragmentCart;
import com.nike.products.view.fragment.FragmentHome;
import com.nike.products.view.fragment.FragmentNotification;
import com.nike.products.view.fragment.FragmentProduct;
import com.nike.products.view.fragment.FragmentProfile;

public class MainActivity extends ActivityBase implements BottomNavigationView.OnNavigationItemSelectedListener {


    ActivityMainBinding mBinding;
    ViewModelMain mViewModel;
    private ActionBar actionBar;
    private ActionBarDrawerToggle mDrawerToggle;

    FragmentProduct fragmentProduct = new FragmentProduct();
    FragmentCart fragmentCart = new FragmentCart();
    FragmentHome fragmentHome = new FragmentHome();
    FragmentBookmark fragmentBookmark = new FragmentBookmark();
    FragmentNotification fragmentNotification = new FragmentNotification();
    FragmentProfile fragmentProfile = new FragmentProfile();

    private final FragmentManager.OnBackStackChangedListener mBackStackChangedListener = this::updateDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            SplashScreen.installSplashScreen(this);
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(ViewModelMain.class);
        setContentView(mBinding.getRoot());

        setDrawer();
        initToolbar();
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        mBinding.bottomNavigationView.setSelectedItemId(R.id.HOME);
        mBinding.setMViewmodel(mViewModel);
    }


    private void setDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drawerLayoutMain, mBinding.toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.setToolbarNavigationClickListener(v -> {
            onBackPressed();
        });
        mBinding.drawerLayoutMain.addDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();

    }

    @SuppressLint("RestrictedApi")
    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            mViewModel.observeVisibility.set(true);
        }

        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.HOME) {
            navigateToHome();
            return true;

        } else if (item.getItemId() == R.id.BOOKMARK) {
            navigateToBookmark();
            return true;

        } else if (item.getItemId() == R.id.NOTIFICATION) {
            navigateToNotification();
            return true;

        } else if (item.getItemId() == R.id.PROFILE) {
            navigateToProfile();
            return true;

        }

        return false;
    }


    private void updateDrawerToggle() {
        boolean isMain = true;
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof FragmentProduct || fragment instanceof FragmentCart) {
            isMain = false;
        } else {
            setCheckedBottom(fragment);
            popBackStack(FragmentProduct.class.getCanonicalName());
            popBackStack(FragmentCart.class.getCanonicalName());


        }

        Log.d("fragments", "  " + getSupportFragmentManager().getFragments().size());
        actionBar.setDisplayHomeAsUpEnabled(!isMain);
        actionBar.setDisplayShowHomeEnabled(isMain);
        actionBar.setDisplayShowTitleEnabled(!isMain);
        mViewModel.observeVisibility.set(isMain);

        manageFragments(fragment);
        try {
            fragment.onResume();

        } catch (Exception e) {

        }


    }

    private void popBackStack(String canonicalName) {
        Fragment fr = getSupportFragmentManager().findFragmentByTag(FragmentProduct.class.getCanonicalName());
        if (fr != null) {
            getSupportFragmentManager().beginTransaction().remove(fr).commit();
        }
    }

    private void setCheckedBottom(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (fragment instanceof FragmentProfile) {
            mBinding.bottomNavigationView.getMenu().getItem(3).setChecked(true);
        } else if (fragment instanceof FragmentBookmark) {
            mBinding.bottomNavigationView.getMenu().getItem(1).setChecked(true);
        } else if (fragment instanceof FragmentHome) {
            mBinding.bottomNavigationView.getMenu().getItem(0).setChecked(true);
        } else if (fragment instanceof FragmentNotification) {
            mBinding.bottomNavigationView.getMenu().getItem(2).setChecked(true);
        }

    }

    public void addFragment(Fragment fragment, String title, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            transaction.add(R.id.frame, fragment, tag);
            transaction.addToBackStack(tag);

        } else {
            transaction.show(fragment);

        }
        manageFragments(fragment);

        transaction.commit();


    }

    private void manageFragments(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();


        if (manager.getFragments().size() > 0) {

            for (int i = 0; i < manager.getFragments().size(); i++) {

                if (!TextUtils.equals(manager.getFragments().get(i).getTag(), fragment.getTag())) {
                    getSupportFragmentManager().beginTransaction().hide(manager.getFragments().get(i)).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(manager.getFragments().get(i)).commit();

                }
            }

        }

    }

    public void setAppTitle(String title) {
        String htmlTitleText = "";
        if (!TextUtils.isEmpty(title)) {
            htmlTitleText = Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            htmlTitleText = "";
        }

        mBinding.toolbar.setTitle(htmlTitleText);
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.frame);
    }


    private void navigateToProfile() {
        addFragment(fragmentProfile, "", FragmentProfile.class.getCanonicalName());

    }

    private void navigateToNotification() {
        addFragment(fragmentNotification, "", FragmentNotification.class.getCanonicalName());
    }

    private void navigateToBookmark() {
        addFragment(fragmentBookmark, "Bookmarks", FragmentBookmark.class.getCanonicalName());
    }

    private void navigateToHome() {
//        getSupportFragmentManager().popBackStack(FragmentHome.class.getCanonicalName(),FragmentManager.POP_BACK_STACK_INCLUSIVE);
        addFragment(fragmentHome, "", FragmentHome.class.getCanonicalName());
    }

    public void navigateToProduct(ModelHome modelHome) {
        fragmentProduct.setData(modelHome);
        addFragment(fragmentProduct, modelHome.getName(), FragmentProduct.class.getCanonicalName());

    }

    public void navigateToCart() {
        addFragment(fragmentCart, "Cart", FragmentCart.class.getCanonicalName());

    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().addOnBackStackChangedListener(mBackStackChangedListener);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
            updateDrawerToggle();
        } else {
            finish();
        }
    }


}