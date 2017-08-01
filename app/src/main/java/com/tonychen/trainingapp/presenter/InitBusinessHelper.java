package com.tonychen.trainingapp.presenter;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tonychen.trainingapp.BuildConfig;
import com.tonychen.trainingapp.DemoApplication;
import com.tonychen.trainingapp.config.Attribute;
import com.tonychen.trainingapp.manager.HandlerManager;

/**
 * Created by TonyChen on 2017/08/01;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public final class InitBusinessHelper {
    private static final String TAG = InitBusinessHelper.class.getSimpleName();
    private static InitBusinessHelper mInstance = new InitBusinessHelper();

    public static InitBusinessHelper getmInstance() {
        return mInstance;
    }

    public void initInSubThread(Application app) {
        HandlerManager.getInstance().getSubThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                runInSubThread();
                Logger.i(" initInSubThread finish");
            }
        });
    }

    /**
     * 应用在初始化阶段没必要再主线程的任务都应放置在此方法中,该方法将在子线程中执行
     */
    private void runInSubThread() {
        if (LeakCanary.isInAnalyzerProcess(DemoApplication.getInstance())) {
            return;
        }
        LeakCanary.install(DemoApplication.getInstance());
    }

    public void initInMainThread(Application app) {
        CrashReport.initCrashReport(app, Attribute.BuglyAppID, BuildConfig.DEBUG);

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new LogcatLogStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("TONYCHEN")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//        Logger.addLogAdapter(new DiskLogAdapter()); // 把log保存到本地
//        Logger.addLogAdapter(new AndroidLogAdapter() {
//            @Override public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
//            }
//        });


        Logger.i("initInMainThread finish");
    }
}
