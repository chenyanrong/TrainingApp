package com.tonychen.trainingapp.utils;

import android.content.Context;

import com.tonychen.trainingapp.DemoApplication;

/**
 * Created by chenc on 2017/8/11.
 */

public class AppUtil {
    private AppUtil() {
    }

    public static final Context getApplicationContext() {
        return DemoApplication.getInstance();
    }
}
