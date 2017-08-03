package com.tonychen.trainingapp.view.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.tonychen.trainingapp.R;

/**
 * Created by TonyChen on 2017/08/03;
 * Email : chenchenyanrong@163.com
 * Blog : http://blog.csdn.net/weixin_37484990
 * Description :
 */

public class RoundRelativeLayout extends RelativeLayout {
    private static final String TAG = RoundRelativeLayout.class.getSimpleName();

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setPersistentDrawingCache(PERSISTENT_ANIMATION_CACHE);
        setAnimationCacheEnabled(false);
        setChildrenDrawnWithCacheEnabled(true);
        setChildrenDrawingCacheEnabled(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(getCirclePath());
        canvas.drawColor(getResources().getColor(R.color.black_light));
        canvas.restore();
        super.onDraw(canvas);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        canvas.clipPath(getCirclePath());
        return super.drawChild(canvas, child, drawingTime);
    }

    private Path getCirclePath() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius = Math.min(width / 2, height / 2);
        Logger.i("radius = " + radius);

        Path path = new Path();
        path.addCircle(width / 2, height / 2, radius, Path.Direction.CCW);
        return path;
    }
}
