package com.tonychen.trainingapp.view.customviews;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tonychen.tonylib.utils.UIUtil;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.manager.FloatWindowManager;
import com.tonychen.trainingapp.view.interf.IFloatView;

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
                UIUtil.dip2px(mContext, 32),
                UIUtil.dip2px(mContext, 32)
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
