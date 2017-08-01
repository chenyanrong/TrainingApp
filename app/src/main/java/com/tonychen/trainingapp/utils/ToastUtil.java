package com.tonychen.trainingapp.utils;

import android.widget.Toast;

import com.tonychen.trainingapp.DemoApplication;

/**
 * Created by TonyChen on 2017/08/01;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class ToastUtil {
    private static Toast toast = null;
    public static void showTextToast( String msg) {
        if (toast == null) {
            toast = Toast.makeText(DemoApplication.getInstance(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}