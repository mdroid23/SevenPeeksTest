package com.sevenpeakssoftware;

import android.app.Application;

import com.sevenpeakssoftware.mahesh.BuildConfig;
import com.sevenpeakssoftware.utils.UtilSingleton;

/**
 * Created by user on 2/16/2018.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        UtilSingleton.init(this, BuildConfig.APPLICATION_ID, String.valueOf(BuildConfig.VERSION_CODE));
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}
