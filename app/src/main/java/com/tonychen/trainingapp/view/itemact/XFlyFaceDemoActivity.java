package com.tonychen.trainingapp.view.itemact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iflytek.cloud.Setting;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;
import com.tonychen.trainingapp.view.subactivity.OnlineFaceDemoActivity;

public class XFlyFaceDemoActivity extends BaseTitleActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfly_face_demo);

        findViewById(R.id.btn_online_demo).setOnClickListener(this);
        findViewById(R.id.btn_offline_demo).setOnClickListener(this);
        findViewById(R.id.btn_video_demo).setOnClickListener(this);

        Setting.setShowLog(true);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_online_demo:
                intent = new Intent(this, OnlineFaceDemoActivity.class);
                startActivity(intent);
                break;
//            case R.id.btn_offline_demo:
//                intent = new Intent(this, OfflineFaceDemo.class);
//                startActivity(intent);
//                break;
//            case R.id.btn_video_demo:
//                intent = new Intent(this, VideoDemo.class);
//                startActivity(intent);
//                break;
            default:
                break;
        }
    }
}
