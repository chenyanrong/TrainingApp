package com.tonychen.trainingapp.view.customviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.tonychen.trainingapp.R;

/**
 * Created by TonyChen on 2017/08/02;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class SimpleTitleView extends RelativeLayout implements View.OnClickListener {
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
        this.removeAllViews();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.comp_activity_title_simple, null, false);
        View vReturn = view.findViewById(R.id.v_return);
        vReturn.setOnClickListener(this);
        this.addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_return:
                ((Activity) getContext()).finish();
            break;
            default:
                break;
        }
    }
}
