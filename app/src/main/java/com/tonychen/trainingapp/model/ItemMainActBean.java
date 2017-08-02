package com.tonychen.trainingapp.model;

/**
 * Created by chenc on 2017/8/1.
 */

public class ItemMainActBean {
    private String clazzName;
    private String title;

    public String getClazzName() {
        return clazzName;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public ItemMainActBean(String clazzName, String title, String intro) {
        this.clazzName = clazzName;
        this.title = title;
        this.intro = intro;
    }

    private String intro;
}
