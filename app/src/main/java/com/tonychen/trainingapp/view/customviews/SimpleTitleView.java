package com.tonychen.trainingapp.view.customviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.tonychen.trainingapp.R;

/**
 * Created by TonyChen on 2017/08/02;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class SimpleTitleView extends RelativeLayout  {

    private android.support.v7.widget.Toolbar mToolbar;

    public SimpleTitleView(Context context) {
        this(context, null);
    }

    public SimpleTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
//        this.removeAllViews();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.comp_activity_title_simple, null, false);
        mToolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.selector_return,null));
        }
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        this.addView(view);
    }

    public void setTitle(String title){
        if(TextUtils.isEmpty(title)){
            title = "TITLE IS NULL";
        }
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        mToolbar.setTitle(title);
    }
}
