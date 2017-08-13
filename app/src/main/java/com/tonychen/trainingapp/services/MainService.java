package com.tonychen.trainingapp.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.IDaemonInterface;
import com.tonychen.trainingapp.IMainInterface;
import com.tonychen.trainingapp.config.Attribute;
import com.tonychen.trainingapp.events.EventOpenDeamonService;
import com.tonychen.trainingapp.utils.SPUtil;
import com.tonychen.trainingapp.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainService extends BaseService {

    private ServiceConnection mDaemonServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Logger.i("onServiceConnected ComponentName = " + componentName.getClassName());
            deamonServiceHolder = IDaemonInterface.Stub.asInterface(iBinder);
            try {
                Logger.i("MainService 当前PID = " + android.os.Process.myPid() + " 绑定的守护进程的PID = " + deamonServiceHolder.getPID());
                ToastUtil.showText("MainService 当前PID = " + android.os.Process.myPid() + " 绑定的守护进程的PID = " + deamonServiceHolder.getPID());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Logger.i("onServiceDisconnected ComponentName = " + componentName.getClassName());
            if ((Boolean) SPUtil.getData(Attribute.ISBINDDAEMONSERVICE, false)) {
                deamonServiceHolder = null;
                startService(new Intent(MainService.this, DaemonService.class));
                Logger.i("MainService 重启 DaemonService");
            }
        }
    };
    private IDaemonInterface deamonServiceHolder;
    private Intent startDaemonService;

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMainServiceHolder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        mMainServiceHolder = new MainServiceHolder();

        startDaemonService = new Intent(MainService.this, DaemonService.class);
        keepAppAlive((Boolean) SPUtil.getData(Attribute.ISBINDDAEMONSERVICE, false));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private MainServiceHolder mMainServiceHolder;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventOpenDeamonService event) {
        keepAppAlive(event.isOpen);
    }

    /**
     * 进程保活
     */
    private void keepAppAlive(final boolean isBindDaemonService) {
        Logger.i("keepAppAlive isBindDaemonService = " + isBindDaemonService);
        if (isBindDaemonService) {
//            /**
//             * 如果已经绑过来了,就没有必要再调用了
//             */
            if (deamonServiceHolder == null) {
            bindService(startDaemonService, mDaemonServiceConnection, BIND_AUTO_CREATE);
            }

        } else {
            if (deamonServiceHolder == null) {
                return;
            }
            try {
                deamonServiceHolder.stopDeamonService();
                ToastUtil.showText("断开与守护进程的连接");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }


    }


    private static final class MainServiceHolder extends IMainInterface.Stub {

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
    }

}
