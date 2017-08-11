package com.tonychen.trainingapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.config.Attribute;
import com.tonychen.trainingapp.events.EventOpenDeamonService;
import com.tonychen.trainingapp.utils.SPUtil;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

import org.greenrobot.eventbus.EventBus;

public class ConfigurationActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }


    @Override
    protected void fetchData() {
        super.fetchData();
    }

    @Override
    protected void initView() {
        super.initView();
        Switch swOpenDeamon = (Switch) findViewById(R.id.sw_opendeamon);
        swOpenDeamon.setChecked((Boolean) SPUtil.getData(Attribute.ISBINDDAEMONSERVICE, false));
        swOpenDeamon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtil.putData(Attribute.ISBINDDAEMONSERVICE, true);
                    EventBus.getDefault().post(new EventOpenDeamonService(true));
                } else {
                    SPUtil.putData(Attribute.ISBINDDAEMONSERVICE, false);
                    EventBus.getDefault().post(new EventOpenDeamonService(false));
                }


            }
        });
    }
}
