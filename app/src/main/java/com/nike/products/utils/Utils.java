package com.nike.products.utils;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.nike.products.R;

public class Utils {
    public static void showSnackBar(View viewLayout, String toastMessage) {
        try {
            Snackbar snackbar;

            snackbar = Snackbar.make(viewLayout, toastMessage, Snackbar.LENGTH_LONG);
//            snackbar.getView().setBackgroundColor(R.color.white);
            snackbar.setBackgroundTint(ContextCompat.getColor(viewLayout.getContext(), R.color.white));
            snackbar.setTextColor(ContextCompat.getColor(viewLayout.getContext(), R.color.black));

            snackbar.show();
        } catch (Exception exception) {
        }




    }
}
