package com.tonychen.trainingapp.view.interf;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.tonychen.tonylib.utils.UIUtil;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.customviews.SimpleTitleView;
import com.tonychen.trainingapp.view.interf.BaseActivity;

/**
 * Created by TonyChen on 2017/08/02;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class BaseTitleActivity extends BaseActivity {

    private View titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();

    }

    /**
     * 初始化Toolbar
     */
    protected void initTitle() {
        titleView = new SimpleTitleView(this);
        titleView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View advanceContentView = LayoutInflater.from(this).inflate(layoutResID, null, false);
        initContentView(advanceContentView);
    }

    private void initContentView(View advanceContentView) {
        LinearLayout finallyContentView = new LinearLayout(this);
        finallyContentView.setFitsSystemWindows(true);
        finallyContentView.addView(titleView,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil.getActionBarHeight(this))
        );
        finallyContentView.addView(advanceContentView,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        );
        super.setContentView(finallyContentView);

    }

    @Override
    public void setContentView(View view) {
        initContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        LinearLayout finallyContentView = new LinearLayout(this);
        finallyContentView.setFitsSystemWindows(true);
        finallyContentView.addView(titleView,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil.getActionBarHeight(this))
        );
        finallyContentView.addView(view, params);
        super.setContentView(finallyContentView);
    }
}
