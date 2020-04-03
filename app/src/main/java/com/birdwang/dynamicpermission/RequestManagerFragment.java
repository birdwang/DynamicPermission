package com.birdwang.dynamicpermission;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;

public class RequestManagerFragment extends Fragment {
    RequestManager requestManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        requestManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
