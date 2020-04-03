package com.birdwang.dynamicpermission;

import android.content.Intent;
import android.support.annotation.NonNull;

public interface Listener {

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
