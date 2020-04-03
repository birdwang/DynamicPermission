package com.birdwang.dynamicpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import static com.birdwang.dynamicpermission.PermissionCall.PERMISSION_REQUEST_CODE;

public class PermissionHandler {

    static boolean checkPermission(Activity activity, String[] permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                int result = ActivityCompat.checkSelfPermission(activity, permission);
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    static void requestPermissions(Context context, String[] permissions, Fragment fragment) {
        if (checkPermission((Activity) context, permissions)){
            fragment.requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }

    static void requestPermissions(Context context, String[] permissions, android.app.Fragment fragment) {
        if (checkPermission((Activity) context, permissions)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fragment.requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }
    }

    static boolean checkEachPermissionsGranted(int[] grantResults) {
        if (grantResults == null || grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
