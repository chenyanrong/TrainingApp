package com.tonychen.trainingapp.services;

import android.app.Service;
import android.content.Intent;

import com.orhanobut.logger.Logger;

/**
 * Created by TonyChen on 2017/8/11;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * PACKAGENAME: com.tonychen.trainingapp.services
 * Description :
 */
public abstract class BaseService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(this.getClass().getSimpleName() + " onCreate!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i(this.getClass().getSimpleName().toString() + " onStartCommand intent = " + intent.toString() + " startId = " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
//        Logger.i(this.getClass().getSimpleName().toString() + " onStart intent = " + intent.toString() + " startId = " + startId);
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i(this.getClass().getSimpleName() + " onDestroy!");
    }

}
