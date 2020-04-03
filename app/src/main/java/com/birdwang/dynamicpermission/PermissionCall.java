package com.birdwang.dynamicpermission;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class PermissionCall {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    static final int PERMISSION_REQUEST_CODE = 11;

    void requestPermission(final Context context, final String[] permissions) {
        if (context instanceof FragmentActivity) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Fragment fragment = ((FragmentActivity) context).getSupportFragmentManager().findFragmentByTag("tag_fragment");
                    if (fragment != null) {
                        PermissionHandler.requestPermissions(context, permissions, fragment);
                    }
                }
            });
        } else if (context instanceof Activity) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    android.app.Fragment fragment = ((Activity) context).getFragmentManager().findFragmentByTag("tag_fragment");
                    if (fragment != null) {
                        PermissionHandler.requestPermissions(context, permissions, fragment);
                    }
                }
            });
        }
    }
}
