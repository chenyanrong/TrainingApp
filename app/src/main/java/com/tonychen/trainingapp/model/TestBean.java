package com.tonychen.trainingapp.model;

/**
 * Created by TonyChen on 2017/8/17;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * PACKAGENAME: com.tonychen.trainingapp.model
 * Description :
 */
public class TestBean {


    /**
     * intentName : 音乐播放
     * extra : {"page_index":1,"songs":{},"song_sum":0,"domain":"music","count":0}
     */

    private String intentName;
    private String extra;

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "intentName='" + intentName + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
