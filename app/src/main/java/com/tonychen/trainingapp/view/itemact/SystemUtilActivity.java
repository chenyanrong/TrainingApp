package com.tonychen.trainingapp.view.itemact;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.tonychen.trainingapp.IMainInterface;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.services.MainService;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

public class SystemUtilActivity extends BaseTitleActivity implements View.OnClickListener {
    IMainInterface iMainInterface;
    private ServiceConnection mConn;
    private Button mHome;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_util);

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();

        Intent intent = new Intent(this, MainService.class);
        mConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                com.orhanobut.logger.Logger.i("onServiceConnected ComponentName = " + name.getClassName());
                iMainInterface = IMainInterface.Stub.asInterface(service);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                com.orhanobut.logger.Logger.i("onServiceDisconnected ComponentName = " + name.getClassName());
            }
        };

        bindService(intent, mConn, BIND_AUTO_CREATE);
    }

    @Override
    protected void initView() {
        super.initView();
        mHome = (Button) findViewById(R.id.btn_home);
        mHome.setOnClickListener(this);
        mBack = (Button) findViewById(R.id.btn_back);
        mBack.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }

    @Override
    public void onClick(View v) {
        if (v == mHome) {

            mMainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        iMainInterface.simulateHome();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }, 10000);


        } else if (v == mBack) {
            mMainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        iMainInterface.simulateBack();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }, 10000);

        }
    }
}
