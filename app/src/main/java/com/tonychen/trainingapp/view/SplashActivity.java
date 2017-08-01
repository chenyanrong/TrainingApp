package com.tonychen.trainingapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.config.Attribute;
import com.tonychen.trainingapp.manager.HandlerManager;

public class SplashActivity extends Activity {

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
        setContentView(R.layout.activity_splash);
        HandlerManager.getInstance().getMainHandler().postDelayed(startMainActivityTask, Attribute.time_showsplash);
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
