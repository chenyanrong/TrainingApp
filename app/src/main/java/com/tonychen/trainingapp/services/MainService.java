package com.tonychen.trainingapp.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.IDaemonInterface;
import com.tonychen.trainingapp.IMainInterface;
import com.tonychen.trainingapp.config.Attribute;
import com.tonychen.trainingapp.events.EventOpenDeamonService;
import com.tonychen.trainingapp.utils.SPUtil;
import com.tonychen.trainingapp.utils.ShellUtils;
import com.tonychen.trainingapp.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;


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
        Logger.d(" onMessageEvent EventOpenDeamonService event.isOpen =" + event.isOpen);
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
                Logger.i("deamonServiceHolder is null!");
                return;
            }
            try {

                deamonServiceHolder.stopDeamonService();
                unbindService(mDaemonServiceConnection);
                deamonServiceHolder = null;
                ToastUtil.showText("断开与守护进程的连接");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

    }


    private final class MainServiceHolder extends IMainInterface.Stub {

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
        public void simulateHome() throws RemoteException {
            Logger.i("simulateHome");
            Intent it = new Intent(Intent.ACTION_MAIN);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.addCategory(Intent.CATEGORY_HOME);
            startActivity(it);
            Logger.i("执行返回到launcher主页的指令");
        }

        @Override
        public void simulateBack() throws RemoteException {
            Logger.i("simulateBack");
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
                Logger.i("执行返回键指令完成");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void setData(String data) throws RemoteException {
            Logger.i("setData start");
            ShellUtils.execCommand("input text 23523434242342424", true);
            Logger.i("setData end");
        }

        @Override
        public void deleData() throws RemoteException {
            Logger.i("del start");
            ShellUtils.execCommand("input keyevent 67", true);
            Logger.i("del end");
            Intent it = new Intent();
            it.putExtra("command","append");
            it.putExtra("value","1234567");
        }

        @Override
        public void clearData() throws RemoteException {
            Logger.i("clear start");
            for (int i = 0; i < 10; i++) {
                ShellUtils.execCommand("input keyevent 67", true);
            }
            Logger.i("clear end");
        }
    }

}
