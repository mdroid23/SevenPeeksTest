package com.sevenpeakssoftware.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilSingleton {

    private static UtilSingleton mUtilSingleton;
    private ConnectivityManager mConnectivityManager;
    private String appName;
    private String appVersion;

    public UtilSingleton(Context context, String appName, String appVersion) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.appName = appName;
        this.appVersion = appVersion;
    }

    public static void init(Context context, String appName, String appVersion) {
        mUtilSingleton = new UtilSingleton(context, appName, appVersion);
    }

    public static UtilSingleton getInstance() {
        return mUtilSingleton;
    }


    public boolean hasConnectivity() {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        return activeNetwork != null;
    }

}
