package com.tonychen.trainingapp.manager;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.tonychen.trainingapp.DemoApplication;

/**
 * Created by chenc on 2017/7/31.
 */

public final class FloatWindowManager {
    private static final String TAG = FloatWindowManager.class.getSimpleName() + "tony";
    private final WindowManager mWindowManager;
    private final WindowManager.LayoutParams mLayoutParams;

    private FloatWindowManager() {
        Log.d(TAG, "FloatWindowManager: instance create");
        mWindowManager = (WindowManager) DemoApplication.getInstance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        // 类型
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        // 设置flag
        int flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN; // 全屏并遮盖系统状态栏
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        mLayoutParams.flags = flags;

        // 不设置这个弹出框的透明遮罩显示为黑色
//        mLayoutParams.format = PixelFormat.TRANSLUCENT;

        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.gravity = Gravity.CENTER;
    }

    public static final FloatWindowManager getInstance() {
        return Inner.mFloatWindowManager;
    }

    private static final class Inner {
        private static FloatWindowManager mFloatWindowManager = new FloatWindowManager();
    }

    public void show(View view) {
        Log.d(TAG, "show: ");
        mWindowManager.addView(view, mLayoutParams);
    }

    public void hide(View view) {
        Log.d(TAG, "hide: ");
        mWindowManager.removeView(view);
    }
}
