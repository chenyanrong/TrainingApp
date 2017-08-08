package com.tonychen.trainingapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.tonychen.trainingapp.utils.ToastUtil;

public class FloatWindowActivity extends BaseTrunkItemActivity {
    private static final String TAG = FloatWindowActivity.class.getSimpleName();


    public static final int CODE_OVERLAY_PERMISSION = 10;


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
