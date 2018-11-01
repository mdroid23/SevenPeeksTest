package com.sevenpeakssoftware.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.sevenpeakssoftware.mahesh.BuildConfig;

import retrofit2.Retrofit;

/**
 * Created by Mahesh Parate on 1/4/2017.
 */

public class LogUtil {
    public static void info(String Tag, String message) {
        if (BuildConfig.BUILD_TYPE.equals("release"))
            return;
        Log.d(Tag, message);
    }

    public static void printObject(Object object) {
        if (BuildConfig.BUILD_TYPE.equals("release"))
            return;
        String string = ((new Gson()).toJson(object));
        LogUtil.info(object.getClass().getSimpleName(), string);
    }

    public static void printRetroFitError(Retrofit error) {
        if (BuildConfig.BUILD_TYPE.equals("release"))
            return;
       // error.printStackTrace();

    }

    public static void printException(Exception exception) {
        if (BuildConfig.BUILD_TYPE.equals("release"))
            return;
        exception.printStackTrace();

    }
}
