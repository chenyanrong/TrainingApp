package com.tonychen.trainingapp.view;

import android.app.Activity;
import android.os.Bundle;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.interf.BaseTitleActivity;

public class KotlinActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin);
    }
}