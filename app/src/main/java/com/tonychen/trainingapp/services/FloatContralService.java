package com.tonychen.trainingapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.tonychen.trainingapp.manager.FloatWindowManager;
import com.tonychen.trainingapp.manager.IFloatContral;

/**
 * Created by chenc on 2017/7/31.
 */

public class FloatContralService extends Service {
    private static final String TAG = FloatContralService.class.getSimpleName() + "TONY";

    private static class FloatContral implements IFloatContral {

        @Override
        public void show(View mFloatView) {
            Log.d(TAG, "showFloat: ");
            FloatWindowManager.getInstance().show(mFloatView);
        }

    }

    private static final class Inner {
        private static FloatContral mFloatContral = new FloatContral();
    }

    public static FloatContral getFloatContralInstance() {
        return Inner.mFloatContral;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
