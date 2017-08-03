package com.tonychen.trainingapp.view.interf;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.tonychen.tonylib.utils.UIUtil;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.config.Attribute;

/**
 * Created by TonyChen on 2017/08/01;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private View mTitleView;

    protected void beforeSetContentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            Logger.i("setStatusBarColor----------");
        }
    }

    protected void afterSetContentView() {
        ViewGroup contentView = getContentView();
//        contentView.getChildAt(0).setFitsSystemWindows(true);
        Logger.d("setFitsSystemWindows----------------");

        if (isKeepScreenOn()) {
            contentView.getChildAt(0).setKeepScreenOn(true);
        }
    }

    protected ViewGroup getContentView() {
        return (ViewGroup) this.findViewById(android.R.id.content);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleView = setTitleView();
        Logger.i(this.getClass().getSimpleName() + "--->onCreate");
    }

    /**
     * 设置标题栏,如果是空,默认无标题栏
     *
     * @return titleView
     */
    protected View setTitleView() {
        return null;
    }

    /**
     * true保持屏幕常亮
     *
     * @return 是否保持屏幕常亮
     */
    protected boolean isKeepScreenOn() {
        return Attribute.isKeepScreenOn;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        beforeSetContentView();
        if (null == mTitleView) {
            super.setContentView(layoutResID);
        } else {
            View advanceContentView = LayoutInflater.from(this).inflate(layoutResID, null, false);
            initContentView(advanceContentView);
        }
        afterSetContentView();
    }

    private void initContentView(View advanceContentView) {
        LinearLayout finallyContentView = new LinearLayout(this);
        finallyContentView.setFitsSystemWindows(true);
        finallyContentView.addView(mTitleView,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil.getActionBarHeight(this))
        );
        finallyContentView.addView(advanceContentView,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        );
        super.setContentView(finallyContentView);
    }

    @Override
    public void setContentView(View view) {
        beforeSetContentView();
        if (mTitleView != null) {
            initContentView(view);
        } else {
            super.setContentView(view);
        }
        afterSetContentView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        beforeSetContentView();
        if (mTitleView != null) {
            LinearLayout finallyContentView = new LinearLayout(this);
            finallyContentView.addView(mTitleView,
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil.getActionBarHeight(this))
            );
            finallyContentView.addView(view, params);
            super.setContentView(finallyContentView);
        } else {
            super.setContentView(view, params);
        }
        afterSetContentView();
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
