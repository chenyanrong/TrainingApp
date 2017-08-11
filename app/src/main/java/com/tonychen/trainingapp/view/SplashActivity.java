package com.tonychen.trainingapp.view;

import android.content.Intent;
import android.os.Bundle;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.config.Attribute;
import com.tonychen.trainingapp.manager.HandlerManager;
import com.tonychen.trainingapp.view.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private Runnable startMainActivityTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandlerManager.getInstance().getMainHandler().postDelayed(startMainActivityTask, Attribute.time_showsplash);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        HandlerManager.getInstance().getMainHandler().postDelayed(startMainActivityTask, Attribute.time_showsplash);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        HandlerManager.getInstance().getMainHandler().removeCallbacks(startMainActivityTask);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
