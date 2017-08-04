package com.tonychen.trainingapp.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.adapter.MainActAdapter;
import com.tonychen.trainingapp.model.ItemMainActBean;
import com.tonychen.trainingapp.utils.ToastUtil;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

public class FloatWindowActivity extends BaseTitleActivity {
    private static final String TAG = FloatWindowActivity.class.getSimpleName();


    private List<ItemMainActBean> mData;

    private static final int CODE_OVERLAY_PERMISSION = 10;
    private String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);
        category = getIntent().getStringExtra(MainActivity.KEY_CATEGORY);
        Logger.d("获取到的category = " + category);
    }


    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, CODE_OVERLAY_PERMISSION);
            }
        }
    }

    @Override
    protected void fetchData() {
        mData = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setAction("com.tonychen.action.demo");
        intent.addCategory("com.tonychen.category.branch");
        if (!TextUtils.isEmpty(category)) {
            intent.addCategory(category);
        }
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
        if (null == resolveInfos || resolveInfos.size() == 0) {
            Logger.e("查询不到任何有效的activity!");
        }
        for (ResolveInfo resolveinfo : resolveInfos) {
            mData.add(
                    new ItemMainActBean(
                            resolveinfo.activityInfo.name,
                            resolveinfo.activityInfo.nonLocalizedLabel.toString(),
                            getResources().getString(resolveinfo.activityInfo.descriptionRes),
                            resolveinfo.priority
                    )
            );
        }
    }

    @Override
    protected void initView() {
        RecyclerView mRecyclerViewContainer = (RecyclerView) findViewById(R.id.rcv_container);
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
                Logger.e("onItemClick: postion" + postion + " className = " + mData.get(postion).getClazzName());
                try {
                    Intent startActIntent = new Intent(FloatWindowActivity.this, Class.forName(mData.get(postion).getClazzName()));
                    startActivity(startActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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
                    ToastUtil.showText("not granted");
                    FloatWindowActivity.this.finish();

                }
            }
        }
    }
}
