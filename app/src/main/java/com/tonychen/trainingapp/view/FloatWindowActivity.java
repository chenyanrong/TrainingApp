package com.tonychen.trainingapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.adapter.MainActAdapter;
import com.tonychen.trainingapp.manager.IFloatContral;
import com.tonychen.trainingapp.model.ItemMainActBean;
import com.tonychen.trainingapp.services.FloatContralService;
import com.tonychen.trainingapp.view.customviews.BaseFloatView;
import com.tonychen.trainingapp.view.customviews.NormalView;
import com.tonychen.trainingapp.view.interf.BaseTitleActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FloatWindowActivity extends BaseTitleActivity {
    private static final String TAG = FloatWindowActivity.class.getSimpleName();

    private IFloatContral mFloatContral;
    private View mFloatView;
    private RecyclerView mRecyclerViewContainer;

    private List<ItemMainActBean> mData;

    private static final int CODE_OVERLAY_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFloatContral = FloatContralService.getFloatContralInstance();

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, CODE_OVERLAY_PERMISSION);
            }
        }
        fetchData();
        initView();

    }

    private void fetchData() {
        mData = new ArrayList<>();
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
        mData.add(new ItemMainActBean(NormalView.class.getName(), NormalView.class.getSimpleName(), "显示一个黑屏的界面,测试~~~~"));
    }

    private void initView() {
        mRecyclerViewContainer = (RecyclerView) findViewById(R.id.rcv_container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewContainer.setLayoutManager(linearLayoutManager);

        mRecyclerViewContainer.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);
        mRecyclerViewContainer.addItemDecoration(dividerItemDecoration);

        MainActAdapter mainActAdapter = new MainActAdapter(mData);
        mainActAdapter.setOnItemonClickListener(new MainActAdapter.OnItemonClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.e(TAG, "onItemClick: postion" + postion);
                try {
                    mFloatView = null;
                    Class<?> clazz = Class.forName(mData.get(postion).getClazzName());
                    Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(Context.class);
                    Object floatViewInstance = declaredConstructor.newInstance(FloatWindowActivity.this);

                    Class<BaseFloatView> baseFloatViewClass = BaseFloatView.class;
                    Method onCreateView = baseFloatViewClass.getDeclaredMethod("createView");
                    onCreateView.setAccessible(true);
                    mFloatView = (View) onCreateView.invoke(floatViewInstance);

                    if (mFloatView == null) {
                        Log.e(TAG, "mFloatView==null");
                        return;
                    }
                    mFloatContral.show(mFloatView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "反射报错了:" + e.getMessage());
                }
            }
        });
        mRecyclerViewContainer.setAdapter(mainActAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    /**
     * 普通的界面
     *
     * @param view
     */
    public void normal(View view) {
        mFloatView = new NormalView(this).createView();
        mFloatContral.show(mFloatView);
    }
}
