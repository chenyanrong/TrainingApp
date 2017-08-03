package com.tonychen.trainingapp.view.itemact;

import android.os.Bundle;
import android.view.View;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.manager.IFloatContral;
import com.tonychen.trainingapp.services.FloatContralService;
import com.tonychen.trainingapp.view.customviews.NormalView;
import com.tonychen.trainingapp.view.interf.BaseTitleActivity;

public class CommonFloatWindowActivity extends BaseTitleActivity {
    private IFloatContral mFloatContral;
    private View mFloatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_float_window);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        mFloatContral = FloatContralService.getFloatContralInstance();
    }


    public void show(View view) {
        normal();
    }

    public void normal() {
        mFloatView = new NormalView(this).createView();
        mFloatContral.show(mFloatView);

//        try {
//                    mFloatView = null;
//                    Class<?> clazz = Class.forName(mData.get(postion).getClazzName());
//                    Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(Context.class);
//                    Object floatViewInstance = declaredConstructor.newInstance(FloatWindowActivity.this);
//
//                    Class<BaseFloatView> baseFloatViewClass = BaseFloatView.class;
//                    Method onCreateView = baseFloatViewClass.getDeclaredMethod("createView");
//                    onCreateView.setAccessible(true);
//                    mFloatView = (View) onCreateView.invoke(floatViewInstance);
//
//                    if (mFloatView == null) {
//                        Logger.e("mFloatView==null");
//                        return;
//                    }
//                    mFloatContral.show(mFloatView);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Logger.e("反射报错了:" + e.getMessage());
//                }
    }
}
