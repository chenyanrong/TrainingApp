package com.tonychen.trainingapp.view.itemact;

import android.os.Bundle;
import android.view.View;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.events.EventDemo;
import com.tonychen.trainingapp.utils.ToastUtil;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusDemoActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventDemo event) {
        ToastUtil.showText(event.getInfo());
    }

    public void sendEvent(View view) {
        EventDemo event = new EventDemo("hello world!");
        EventBus.getDefault().post(event);
    }


    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
