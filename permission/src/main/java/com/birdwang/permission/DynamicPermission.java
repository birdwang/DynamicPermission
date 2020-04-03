package com.birdwang.permission;

import android.app.Activity;
import android.content.Context;

public class DynamicPermission {
    private RequestManagerRetriever requestManagerRetriever;

    private DynamicPermission(){
        requestManagerRetriever = new RequestManagerRetriever();
    }

    public static DynamicPermission getInstance(){
        return Holder.instance;
    }

    private static class Holder{
        private static final DynamicPermission instance = new DynamicPermission();
    }

    private RequestManagerRetriever getRequestManagerRetriever(){
        return requestManagerRetriever;
    }

    public void requestPermissions(Context context, String[] permissions, Result result){
        assertActivity(context);
        RequestManager requestManager = getRequestManagerRetriever().fetchRequestManager(context);
        if (requestManager != null){
            requestManager.requestPermission(context, permissions, result);
        }
    }

    private void assertActivity(Context context){
        if (!(context instanceof Activity)){
            throw new IllegalArgumentException("Context must be subclass of Activity");
        }
    }
}
