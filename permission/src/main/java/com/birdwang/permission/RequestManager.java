package com.birdwang.permission;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import static com.birdwang.permission.PermissionCall.PERMISSION_REQUEST_CODE;

class RequestManager implements Listener {
    private final PermissionCall call = new PermissionCall();
    private Result result;

    void requestPermission(Context context, String[] permissions, Result result) {
        this.result = result;
        if (!needRequest(context, permissions)) {
            if (result != null) {
                result.onGrant();
            }
        } else {
            call.requestPermission(context, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (result != null && requestCode == PERMISSION_REQUEST_CODE) {
            if (PermissionHandler.checkEachPermissionsGranted(grantResults)) {
                result.onGrant();
            } else {
                result.onDeny();
            }
        }
    }

    private boolean needRequest(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                !PermissionHandler.checkPermission(((Activity) context), permissions)) {
            return false;
        }
        return true;
    }
}
