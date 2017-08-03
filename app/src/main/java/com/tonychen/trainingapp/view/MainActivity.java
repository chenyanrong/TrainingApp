package com.tonychen.trainingapp.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.adapter.MainActAdapter;
import com.tonychen.trainingapp.model.ItemMainActBean;
import com.tonychen.trainingapp.utils.ToastUtil;
import com.tonychen.trainingapp.view.interf.BaseActivity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    static {
        System.loadLibrary("native-lib");
    }

    private List<ItemMainActBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        initView();
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void fetchData() {
        mData = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setAction("com.tonychen.action.demo");
        intent.addCategory("com.tonychen.category.trunk");
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER);
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

    private void initView() {
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
                Log.e(TAG, "onItemClick: postion" + postion);
//                try {
//                    mFloatView = null;
//                    Class<?> clazz = Class.forName(mData.get(postion).getClazzName());
//                    Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(Context.class);
//                    Object floatViewInstance = declaredConstructor.newInstance(MainActivity.this);
//
//                    Class<BaseFloatView> baseFloatViewClass = BaseFloatView.class;
//                    Method onCreateView = baseFloatViewClass.getDeclaredMethod("createView");
//                    onCreateView.setAccessible(true);
//                    mFloatView = (View) onCreateView.invoke(floatViewInstance);
//
//                    if (mFloatView == null) {
//                        Log.e(TAG, "mFloatView==null");
//                        return;
//                    }
//                    mFloatContral.show(mFloatView);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.e(TAG, "反射报错了:" + e.getMessage());
//                }
                try {
                    startActivity(
                            new Intent(MainActivity.this, Class.forName(mData.get(postion).getClazzName()))
                    );
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        mRecyclerViewContainer.setAdapter(mainActAdapter);

    }

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

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
