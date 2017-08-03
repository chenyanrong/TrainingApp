package com.tonychen.trainingapp.view;

import android.os.Bundle;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.interf.BaseTitleActivity;

public class RxJavaActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }
}
