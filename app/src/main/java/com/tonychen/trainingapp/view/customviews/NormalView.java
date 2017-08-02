package com.tonychen.trainingapp.view.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tonychen.trainingapp.R;

/**
 * Created by TonyChen on 2017/07/31;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class NormalView extends BaseFloatView {


    public NormalView(Context context) {
        super(context);
    }

    @Override
    public ViewGroup onCreateView() {
        return (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.normal, null);
    }

}
