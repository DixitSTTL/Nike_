package com.nike.products.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;


import com.nike.products.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {
    private static AlertDialog mAlertDialogPermission;

    public static boolean isPermissionWithGranted(String[] permission, int requestCode, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> needPermission = checkPermissionList(permission, activity);
            boolean isGranted = !(needPermission != null && !needPermission.isEmpty());
            if (isGranted) {
                return true;
            } else {
                try {
                    ActivityCompat.requestPermissions(activity, needPermission.toArray(new String[needPermission.size()]), requestCode);
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
                return false;
            }
        } else {
            return true;
        }
    }

    public static List<String> checkPermissionList(String[] permission, Context context) {
        List<String> needPermission = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permission != null && permission.length > 0) {
            needPermission = new ArrayList<>();
            for (String aPermission : permission) {
                if (ActivityCompat.checkSelfPermission(context, aPermission) != PackageManager.PERMISSION_GRANTED) {
                    needPermission.add(aPermission);
                }
            }
        }
        return needPermission;
    }

    /*public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            int flag = checkPermissionResult(permissions, grantResults,
                    R.string.request_read_storage_permission);
            if (flag == 1) {
                navigateToBackPermission();
            } *//*else if (flag == 0) {
                //check Permission again
            }*//*
        }
    }*/

    public static int checkPermissionResult(String[] permissions, int[] grantResults, @StringRes int rationalMessage, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean anyPermissionDenied = false;
            boolean neverAskAgainSelected = false;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    anyPermissionDenied = true;
                    neverAskAgainSelected = neverAskAgain(i, permissions, activity);
                }
            }

            if (anyPermissionDenied) {
                if (neverAskAgainSelected) {
                    showAlertPermissions(rationalMessage, activity);
                    return -1;
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean neverAskAgain(int i, String[] permissions, Activity activity) {
        boolean neverAskAgainSelected = !activity.shouldShowRequestPermissionRationale(permissions[i]);
        return neverAskAgainSelected;
    }

    public static void showAlertPermissions(int rationalMessage, Activity activity) {
        boolean isShow = true;
        if (mAlertDialogPermission != null && mAlertDialogPermission.isShowing()) {
            TextView tv_message = mAlertDialogPermission.findViewById(android.R.id.message);
            if (tv_message != null && !TextUtils.equals(tv_message.getText(), activity.getString(rationalMessage))) {
                tv_message.setText(activity.getString(rationalMessage));
            }
            isShow = false;
        }

        if (isShow) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.DialogTheme));
            alertDialog.setTitle(R.string.message_permission_not_granted);
            alertDialog.setMessage(rationalMessage);
            alertDialog.setCancelable(false);
            alertDialog.setNeutralButton(android.R.string.ok, (dialog, which) -> {
                Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivity(i);
            });
            mAlertDialogPermission = alertDialog.create();
            mAlertDialogPermission.show();
        }
    }
}
