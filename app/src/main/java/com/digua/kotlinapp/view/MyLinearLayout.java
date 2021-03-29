package com.digua.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.digua.kotlinapp.utils.LoginUtil;

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/29
 */
public class MyLinearLayout extends LinearLayout {

    private Paint mPaint;

    private static final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#973311"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void draw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG,"draw-before-1-");
        super.draw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,50,mPaint);
        LoginUtil.INSTANCE.e(TAG,"draw-after-2-");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG,"onDraw-before-1-");
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#1245f0"));
        canvas.drawCircle(getWidth()/2,getHeight()/2,30,mPaint);
        LoginUtil.INSTANCE.e(TAG,"onDraw-after-2-");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LoginUtil.INSTANCE.e(TAG,"onLayout-before-1-");
        super.onLayout(changed, l, t, r, b);
        LoginUtil.INSTANCE.e(TAG,"onLayout-after-2-");
    }
}
