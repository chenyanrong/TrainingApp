package com.tonychen.trainingapp.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseTrunkItemActivity extends BaseTitleActivity {
    private static final String TAG = BaseTrunkItemActivity.class.getSimpleName();


    protected List<ItemMainActBean> mData;


    protected String category = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trunk);
    }

    @Override
    protected void afterSetContentView() {
        category = getIntent().getStringExtra(MainActivity.KEY_CATEGORY);
        Logger.d("获取到的category = " + category);
        super.afterSetContentView();
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
                    Intent startActIntent = new Intent(BaseTrunkItemActivity.this, Class.forName(mData.get(postion).getClazzName()));
                    startActivity(startActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        mRecyclerViewContainer.setAdapter(mainActAdapter);
    }

}
