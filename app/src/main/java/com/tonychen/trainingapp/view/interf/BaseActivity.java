package com.tonychen.trainingapp.view.interf;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.R;

/**
 * Created by TonyChen on 2017/08/01;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        Logger.i(this.getClass().getSimpleName() + "--->onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i(this.getClass().getSimpleName() + "--->onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.i(this.getClass().getSimpleName() + "--->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(this.getClass().getSimpleName() + "--->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i(this.getClass().getSimpleName() + "--->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i(this.getClass().getSimpleName() + "--->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i(this.getClass().getSimpleName() + "--->onDestroy");
    }
}
