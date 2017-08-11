package com.tonychen.trainingapp.view.customviews;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by TonyChen on 2017/8/11;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * PACKAGENAME: com.tonychen.trainingapp.view.customviews
 * Description :
 */
public class BaseCustomView extends View {
    public BaseCustomView(Context context) {
        this(context, null);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Logger.i(this.getClass().getSimpleName() + " methondname onMeasure widthMeasureSpec "
                + widthMeasureSpec + " heightMeasureSpec = " + heightMeasureSpec + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Logger.i(this.getClass().getSimpleName() + " methondname onLayout changed "
                + changed + " left = " + left + " top = " + top + " right = " + right +
                " bottom = " + bottom + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        Logger.i(this.getClass().getSimpleName() + " methondname setVisibility visibility "
                + visibility + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Logger.i(this.getClass().getSimpleName() + " methondname computeScroll getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.i(this.getClass().getSimpleName() + " methondname onConfigurationChanged newConfig = "
                + newConfig + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Logger.i(this.getClass().getSimpleName() + " methondname onFocusChanged gainFocus = "
                + gainFocus + " direction = " + direction + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Logger.i(this.getClass().getSimpleName() + " methondname onWindowFocusChanged hasWindowFocus = " + hasWindowFocus + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Logger.i(this.getClass().getSimpleName() + " methondname onWindowVisibilityChanged visibility = " + visibility + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.i(this.getClass().getSimpleName() + " methondname onDetachedFromWindow getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Logger.i(this.getClass().getSimpleName() + " methondname onAttachedToWindow getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.i(this.getClass().getSimpleName() + " methondname onTouchEvent event = " + event.getAction() + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.i(this.getClass().getSimpleName() + " methondname onDraw getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.i(this.getClass().getSimpleName() + " methondname onSizeChanged w = " + w + " h = " + h +
                " oldw = " + oldw + " oldh = " + oldh + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Logger.i(this.getClass().getSimpleName() + " methondname onKeyDown keyCode = " + keyCode + " event = " + event.getAction() + " getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Logger.i(this.getClass().getSimpleName() + " methondname onFinishInflate getMeasureWidth = " +
                getMeasuredWidth() + " getWidth = " + getWidth() + " getMeasuredHeight = " +
                getMeasuredHeight() + " getHeight = " + getHeight());
    }
}
