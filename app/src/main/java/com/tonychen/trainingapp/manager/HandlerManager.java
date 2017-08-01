package com.tonychen.trainingapp.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by TonyChen on 2017/08/01;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class HandlerManager {
    private static final String TAG = HandlerManager.class.getSimpleName();

    private HandlerManager() {
        mMainHandler = new Handler(Looper.getMainLooper());
        mSubThread = new HandlerThread("DemoSubThread");
        mSubThread.start();
        mSubThreadHandler = new Handler(mSubThread.getLooper());
    }

    private static final class Inner {
        private static final HandlerManager mHandlerManagerInstance = new HandlerManager();
    }

    public static HandlerManager getInstance() {
        return Inner.mHandlerManagerInstance;
    }

    private Handler mMainHandler;


    public Handler getMainHandler() {
        return mMainHandler;
    }

    private HandlerThread mSubThread;
    private Handler mSubThreadHandler;

    public Handler getSubThreadHandler() {
        return mSubThreadHandler;
    }

}
