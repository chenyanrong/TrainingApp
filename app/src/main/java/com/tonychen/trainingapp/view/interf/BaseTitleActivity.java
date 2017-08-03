package com.tonychen.trainingapp.view.interf;

import android.view.View;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.customviews.SimpleTitleView;

/**
 * Created by TonyChen on 2017/08/02;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class BaseTitleActivity extends BaseActivity {
    @Override
    protected View setTitleView() {
        View titleView = new SimpleTitleView(this);
        titleView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        return titleView;
    }
}
