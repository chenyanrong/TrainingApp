package com.tonychen.floatwindow.views;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tonychen.floatwindow.managers.FloatWindowManager;
import com.tonychen.floatwindow.R;
import com.tonychen.floatwindow.utils.UIUtils;
import com.tonychen.floatwindow.views.interf.IFloatView;

/**
 * Created by TonyChen on 2017/07/31;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public abstract class BaseFloatView implements IFloatView {
    private ViewGroup mView;
    protected Context mContext;

    public BaseFloatView(Context context) {
        this.mContext = context;
    }

    public abstract ViewGroup onCreateView();

    @Override
    public View createView() {
        mView = onCreateView();
        if (!(mView instanceof RelativeLayout)) {
            throw new RuntimeException("最外层布局必须instanceof RelativieLayout");
        }
        View closeView = new View(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                UIUtils.dip2px(mContext, 32),
                UIUtils.dip2px(mContext, 32)
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            closeView.setBackground(mContext.getResources().getDrawable(R.drawable.selector_close));
        }
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); // 右对齐
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatWindowManager.getInstance().hide(mView);
            }
        });
        mView.addView(closeView, layoutParams);
        return mView;
    }

}
