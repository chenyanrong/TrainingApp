package com.tonychen.trainingapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.interf.BaseTitleActivity;

public class AndroidSystemActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_system);
    }
}
