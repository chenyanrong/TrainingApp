package com.tonychen.trainingapp.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.IDaemonInterface;
import com.tonychen.trainingapp.IMainInterface;

/**
 * 守护进程
 */
public class DaemonService extends BaseService {
    private ServiceConnection mMainServiceConnection;
    private IMainInterface mainServiceHolder;
    private Intent itMainService;
    private static DaemonService mInstance;

    private Runnable checkAliveTask = new Runnable() {
        @Override
        public void run() {
//            Logger.d("DaemonService PID = " + android.os.Process.myPid() + " is alive ===>>>> " + System.currentTimeMillis());
            mMainHandler.postDelayed(this, 3000);
        }
    };

    private Handler mMainHandler = new Handler();

    public DaemonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mDaemonServiceHolder;
    }

    private DaemonServiceHolder mDaemonServiceHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        itMainService = new Intent(this, MainService.class);
        mDaemonServiceHolder = new DaemonServiceHolder();

        mMainServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Logger.i("onServiceConnected ComponentName = " + componentName.getClassName());
                mainServiceHolder = IMainInterface.Stub.asInterface(iBinder);
                try {
                    Logger.i("DaemonService 当前PID = " + android.os.Process.myPid() +
                            " 绑定的应用主进程的PID = " + mainServiceHolder.getPID());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Logger.i("onServiceDisconnected ComponentName = " + componentName.getClassName());
                mainServiceHolder = null;
                startService(new Intent(DaemonService.this, MainService.class));
                Logger.i("DaemonService 重启 MainService");

            }
        };
        if (mainServiceHolder == null) {
            bindService(itMainService, mMainServiceConnection, BIND_AUTO_CREATE);
        }

        mMainHandler.post(checkAliveTask);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mInstance = null;
        if (mMainHandler != null) {
            mMainHandler.removeCallbacks(checkAliveTask);
            mMainHandler = null;
        }
    }

    private static final class DaemonServiceHolder extends IDaemonInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) throws RemoteException {
            Logger.i("basicTypes anInt = " + anInt + " aLong = " + aLong + " aBoolean = " +
                    aBoolean + " aFloat = " + aFloat + " aDouble = " + aDouble + " aString = " +
                    aString);
        }

        @Override
        public int getPID() throws RemoteException {

            return android.os.Process.myPid();
            //获取进程pid
        }

        @Override
        public void stopDeamonService() throws RemoteException {
            if (mInstance != null) {
                mInstance.stopSelf();
                Logger.i("stopDeamonService");
            }
        }


    }
}
