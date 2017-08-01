package com.tonychen.trainingapp;

import android.app.Application;
import com.tonychen.trainingapp.presenter.InitBusinessHelper;

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

    private static  Application mInstance ;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initApp();
    }

    private void initApp() {
        InitBusinessHelper.getmInstance().initInMainThread(this);
        InitBusinessHelper.getmInstance().initInSubThread(this);
    }
}
