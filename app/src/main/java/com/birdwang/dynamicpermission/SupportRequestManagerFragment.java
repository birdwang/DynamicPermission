package com.birdwang.dynamicpermission;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class SupportRequestManagerFragment extends Fragment {
    RequestManager requestManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        requestManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
