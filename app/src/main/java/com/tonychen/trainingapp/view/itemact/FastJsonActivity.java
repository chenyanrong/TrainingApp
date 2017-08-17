package com.tonychen.trainingapp.view.itemact;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.model.Music;
import com.tonychen.trainingapp.model.TestBean;
import com.tonychen.trainingapp.model.TestBean2;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

import java.io.IOException;
import java.io.InputStream;

public class FastJsonActivity extends BaseTitleActivity {

//    private static final String jsonStr = "{\"intentName\":\"音乐播放\",\"extra\":\"{\\\"page_index\\\":1,\\\"songs\\\":{},\\\"song_sum\\\":0,\\\"domain\\\":\\\"music\\\",\\\"count\\\":0}\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);
        //Return an AssetManager instance for your application's package
        InputStream is;
        String jsonStr = "";
        int size = 0;
        try {
            is = getAssets().open("json.txt");
            size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            jsonStr = new String(buffer, "utf-8");
            Logger.i("jsonStr = " + jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        TestBean2 testBean = new Gson().fromJson(jsonStr, TestBean2.class);
//        Logger.i("TESTBEAN = " + testBean);

//        jsonStr = testBean.getExtra();

        TestBean2 musicBean = JSON.parseObject(jsonStr, TestBean2.class);
        Logger.i("fastjson == > " + musicBean.toString());

        TestBean2 musicBean2 = new Gson().fromJson(jsonStr, TestBean2.class);
        Logger.i("Gson == > " + musicBean2.toString());

        Logger.i("FastJson.toJson" + JSON.toJSON(musicBean2));
        Logger.i("Gson.toJson" + new Gson().toJson(musicBean2));
    }
}
