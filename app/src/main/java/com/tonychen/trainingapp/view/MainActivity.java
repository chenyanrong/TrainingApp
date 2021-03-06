package com.tonychen.trainingapp.view;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.adapter.MainActAdapter;
import com.tonychen.trainingapp.model.ItemMainActBean;
import com.tonychen.trainingapp.utils.ToastUtil;
import com.tonychen.trainingapp.view.base.BaseActivity;
import com.tonychen.trainingapp.view.customviews.ShimmerFrameLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    static {
        System.loadLibrary("native-lib");
    }

    private List<ItemMainActBean> mData;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void fetchData() {
        mData = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setAction("com.tonychen.action.demo");
        intent.addCategory("com.tonychen.category.trunk");
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
        for (ResolveInfo resolveinfo : resolveInfos) {
            int priority = -1;
            String category = "";
            try {
                ComponentName componentName = new ComponentName(resolveinfo.activityInfo.packageName, resolveinfo.activityInfo.name);
                ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA);
                priority = activityInfo.metaData.getInt("priority");
                category = activityInfo.metaData.getString("category");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            mData.add(
                    new ItemMainActBean(
                            resolveinfo.activityInfo.name,
                            resolveinfo.activityInfo.nonLocalizedLabel.toString(),
                            getResources().getString(resolveinfo.activityInfo.descriptionRes),
                            priority, category
                    )
            );
        }
        // 数据重新排序
        Collections.sort(mData, new Comparator<ItemMainActBean>() {
            @Override
            public int compare(ItemMainActBean o1, ItemMainActBean o2) {
                if (o1.getPriority() == o2.getPriority()) {
                    return o1.getClazzName().compareTo(o2.getClazzName());
                } else if (o1.getPriority() > o2.getPriority()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != shimmerFrameLayout) {
            shimmerFrameLayout.startShimmerAnimation();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != shimmerFrameLayout) {
            shimmerFrameLayout.stopShimmerAnimation();
        }
    }

    @Override
    protected void initView() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        ToastUtil.showText("click nav_camera");
                        return true;
                    case R.id.nav_gallery:
                        ToastUtil.showText("click nav_gallery");
                        return true;
                    case R.id.nav_manage:
                        ToastUtil.showText("click nav_manage");
                        return true;
                    case R.id.nav_send:
                        ToastUtil.showText("click nav_send");
                        return true;
                    case R.id.nav_share:
                        ToastUtil.showText("click nav_share");
                        return true;
                    case R.id.nav_slideshow:
                        ToastUtil.showText("click nav_slideshow");
                        return true;
                }
                return false;
            }
        });


        shimmerFrameLayout = (ShimmerFrameLayout) navigationView.getHeaderView(0).findViewById(R.id.sfl_container);
//        shimmerFrameLayout.setBaseAlpha(0.1f);
//        shimmerFrameLayout.setDropoff(0.1f);
//        shimmerFrameLayout.setTilt(0);

        shimmerFrameLayout.setDuration(1800);
        shimmerFrameLayout.setRepeatMode(ObjectAnimator.REVERSE);

//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

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
                Logger.i("将打开的Activity = " + mData.get(postion).getClazzName());
                try {
                    Intent startActIntent = new Intent(MainActivity.this, Class.forName(mData.get(postion).getClazzName()));
                    startActIntent.putExtra("KEY_CATEGORY", mData.get(postion).getCategory());
                    startActivity(startActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        mRecyclerViewContainer.setAdapter(mainActAdapter);
    }


    public static final String KEY_CATEGORY = "KEY_CATEGORY";

    /**
     * 通过反射，设置menu显示icon
     *
     * @param view xxx
     * @param menu xxx
     * @return xxx
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ToastUtil.showText("click menu setting");
            return true;
        } else if (id == R.id.action_share) {
            ToastUtil.showText("click menu share");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private long firstClickBack = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondClickBack = System.currentTimeMillis();
            if (secondClickBack - firstClickBack > 1500) {
                ToastUtil.showText("再按一次退出应用!");
                firstClickBack = secondClickBack;
                return true;
            } else {
//                MyApplication.getInstance().exitApp();
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
