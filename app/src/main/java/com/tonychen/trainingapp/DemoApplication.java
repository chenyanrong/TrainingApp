package com.tonychen.trainingapp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tonychen.trainingapp.presenter.InitBusinessHelper;

import java.util.List;

/**
 * Created by TonyChen on 2017/08/01;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class DemoApplication extends Application {
    private static final String TAG = DemoApplication.class.getSimpleName();

    public static Application getInstance() {
        return mInstance;
    }

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 解决多进程,重复初始化application的问题
         */
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    if (procInfo.processName.equals("com.tonychen.trainingapp")) {
                        mInstance = this;
                        initApp();
                        Logger.i("process name is " + procInfo.processName);
                    } else if (procInfo.processName.equals("com.tonychen.trainingapp:daemonprocess")) {
                        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                                .methodCount(0)         // (Optional) How many method line to show. Default 2
                                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
                                .logStrategy(new LogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                                .tag("TonyDebugLog")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                                .build();
                        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
                        Logger.i("process name is " + procInfo.processName);
                    }
                }
            }
        }
    }

    private void initApp() {
        InitBusinessHelper.getmInstance().initInMainThread(this);
        InitBusinessHelper.getmInstance().initInSubThread(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Logger.i("DemoApplication onTrimMemory level = " + level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Logger.e("DemoApplication onTerminate ");
    }
}
