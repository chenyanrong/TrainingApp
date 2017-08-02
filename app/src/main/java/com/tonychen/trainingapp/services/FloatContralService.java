package com.tonychen.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

;import com.tonychen.floatwindow.managers.FloatWindowManager;
import com.tonychen.floatwindow.managers.IFloatContral;

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

//        @Override
//        public void hide(View mFloatView) {
//            Log.d(TAG, "hideFloat: ");
//            FloatWindowManager.getInstance().hide(mFloatView);
//        }
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
