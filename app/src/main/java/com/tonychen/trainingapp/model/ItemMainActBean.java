package com.tonychen.trainingapp.model;

/**
 * Created by chenc on 2017/8/1.
 */

public class ItemMainActBean {
    private String clazzName;
    private String title;
    private int priority;

    public String getClazzName() {
        return clazzName;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public int getPriority() {
        return priority;
    }

    public ItemMainActBean(String clazzName, String title, String intro) {
        this(clazzName, title, intro, 0);
    }

    public ItemMainActBean(String clazzName, String title, String intro, int priority) {
        this.clazzName = clazzName;
        this.title = title;
        this.intro = intro;
        this.priority = priority;
    }

    private String intro;
}
