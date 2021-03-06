package com.digua.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.digua.kotlinapp.utils.LoginUtil;

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/29
 */
public class CustomeLayout extends FrameLayout {

    private Paint mPaint;

    private static final String TAG = "CustomeLayout---";

    public CustomeLayout(Context context) {
        this(context,null);
    }

    public CustomeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#973311"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void draw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG,"--draw-before-1-");
        super.draw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,80,mPaint);
        LoginUtil.INSTANCE.e(TAG,"--draw-after-2-");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#1245f0"));
        LoginUtil.INSTANCE.e(TAG,"--onDraw-before-1-");
        canvas.drawCircle(getWidth()/2,getHeight()/2,100,mPaint);
        super.onDraw(canvas);
        LoginUtil.INSTANCE.e(TAG,"--onDraw-after-2-");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LoginUtil.INSTANCE.e(TAG,"--onLayout--");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LoginUtil.INSTANCE.e(TAG,"--onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LoginUtil.INSTANCE.e(TAG,"--dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LoginUtil.INSTANCE.e(TAG,"--onTouchEvent");
        return super.onTouchEvent(event);
    }
}
