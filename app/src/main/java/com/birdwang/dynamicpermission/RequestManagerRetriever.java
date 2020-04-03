package com.birdwang.dynamicpermission;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever {
    private static final String TAG = "tag_fragment";
    private Map<FragmentManager, SupportRequestManagerFragment> handlingSupportTransaction = new HashMap<>();
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private Map<android.app.FragmentManager, RequestManagerFragment> handlingTransaction = new HashMap<>();

    RequestManager fetchRequestManager(Context context){
        if (context != null && onUiThread()){
            if (context instanceof FragmentActivity){
                return fetchRequestManager(((FragmentActivity) context));
            }else if (context instanceof Activity){
                return fetchRequestManager(((Activity) context));
            }
        }
        return null;
    }

    private RequestManager fetchRequestManager(FragmentActivity activity){
        if (activity != null && !activity.isDestroyed()){
            SupportRequestManagerFragment fragment = fetchSupportFragment(activity.getSupportFragmentManager());
            RequestManager requestManager = fragment.requestManager;
            if (requestManager == null){
                requestManager = new RequestManager();
                fragment.requestManager = requestManager;
            }
            return requestManager;
        }
        return null;
    }

    private RequestManager fetchRequestManager(Activity activity){
        if (activity != null && !activity.isDestroyed()){
            RequestManagerFragment fragment = fetchFragment(activity.getFragmentManager());
            RequestManager requestManager = fragment.requestManager;
            if (requestManager == null){
                requestManager = new RequestManager();
                fragment.requestManager = requestManager;
            }
            return requestManager;
        }
        return null;
    }

    private SupportRequestManagerFragment fetchSupportFragment(final FragmentManager fm){
        SupportRequestManagerFragment fragment = (SupportRequestManagerFragment) fm.findFragmentByTag(TAG);
        if (fragment == null){
            fragment = handlingSupportTransaction.get(fm);
            if (fragment == null){
                fragment = new SupportRequestManagerFragment();
                fm.beginTransaction().add(fragment, TAG).commitAllowingStateLoss();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        handlingSupportTransaction.remove(fm);
                    }
                });
            }
        }
        return fragment;
    }

    private RequestManagerFragment fetchFragment(final android.app.FragmentManager fm){
        RequestManagerFragment fragment = (RequestManagerFragment) fm.findFragmentByTag(TAG);
        if (fragment == null){
            fragment = handlingTransaction.get(fm);
            if (fragment == null){
                fragment = new RequestManagerFragment();
                fm.beginTransaction().add(fragment, TAG).commitAllowingStateLoss();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        handlingTransaction.remove(fm);
                    }
                });
            }
        }
        return fragment;
    }

    private boolean onUiThread(){
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
