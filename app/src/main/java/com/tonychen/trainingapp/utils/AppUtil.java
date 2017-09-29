package com.tonychen.trainingapp.utils;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.tonychen.trainingapp.DemoApplication;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by chenc on 2017/8/11.
 */

public class AppUtil {
    public static final String TAG = AppUtil.class.getSimpleName();

    private AppUtil() {
    }

    public static final Context getApplicationContext() {
        return DemoApplication.getInstance();
    }


    /**
     * 获取栈顶的进程信息
     */
    public static void getTopTask() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
            String shortClassName = info.topActivity.getShortClassName();    //类名
            String className = info.topActivity.getClassName();              //完整类名
            String packageName = info.topActivity.getPackageName();
            Intent it = new Intent();
            it.putExtra()
            //包名
            Log.e("test", "YYSDK className:" + className);
            Log.e("test", "YYSDK shortClassName :" + shortClassName);
            Log.e("test", "YYSDK packageName:" + packageName);
        } else {
            String topPackageName;
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            // We get usage stats for the last 10 seconds
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time);
            // Sort the stats by the last time used
            if (stats != null) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    Log.e("TopPackage Name", topPackageName);
                }
            }
        }
    }


}
