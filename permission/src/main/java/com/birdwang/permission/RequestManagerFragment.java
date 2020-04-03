package com.birdwang.permission;

import android.app.Fragment;
import android.support.annotation.NonNull;

public class RequestManagerFragment extends Fragment {
    RequestManager requestManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        requestManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
