package com.digua.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.digua.kotlinapp.utils.LoginUtil;

/**
 * 重写draw、onDraw、dispatchDraw三个方法测试绘制顺序
 * 日志输出如下：
 *
 * E/MyLinearLayout: onLayout-before-1-
 * MyLinearLayout: onLayout-after-2-
 *
 * MyLinearLayout: draw-before-1-
 * MyLinearLayout: onDraw-before-1-
 * MyLinearLayout: onDraw-after-2-
 * MyLinearLayout: dispatchDraw-before-1-
 * MyLinearLayout: dispatchDraw-after-2-
 * MyLinearLayout: draw-after-2-
 *
 * 由此可知：draw 方法的在super.draw之前的代码最先执行，然后是onDraw方法执行（super.onDraw前后的代码顺序执行）
 * 接着是dispatchDraw方法中dispatchDraw前后的代码顺序执行，最后，draw的super.draw之后的代码才执行。
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
        TextView textView = new TextView(context);
        textView.setText("使用addView 添加一个TextView");
        addView(textView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void draw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG,"draw-before-1-");
        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawCircle(getWidth()/2,getHeight()/2,240,mPaint);
        mPaint.setTextSize(38);
        String text = "我是一个纯绿色的圆";
        mPaint.setColor(Color.parseColor("#ffffff"));//设置字体颜色
        canvas.drawText(text,0,text.length(),(getWidth()-240)/2,getHeight()/2,mPaint);
        super.draw(canvas);
        LoginUtil.INSTANCE.e(TAG,"draw-after-2-");
    }

    @Override
    public void setBackgroundColor(int color) {
        LoginUtil.INSTANCE.e(TAG,"setBackgroundColor-before-1-"+color);
        super.setBackgroundColor(color);
        LoginUtil.INSTANCE.e(TAG,"setBackgroundColor-after-2-"+color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG,"onDraw-before-1-");
        mPaint.setColor(Color.parseColor("#ff0012"));
        canvas.drawCircle(getWidth()/3,getHeight()/3,160,mPaint);
        super.onDraw(canvas);
        LoginUtil.INSTANCE.e(TAG,"onDraw-after-2-");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG,"dispatchDraw-before-1-");
        mPaint.setColor(Color.parseColor("#126590"));
        canvas.drawCircle(getWidth()/7*4,getHeight()/3,160,mPaint);
        super.dispatchDraw(canvas);
        LoginUtil.INSTANCE.e(TAG,"dispatchDraw-after-2-");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LoginUtil.INSTANCE.e(TAG,"onLayout-before-1-");
        super.onLayout(changed, l, t, r, b);
        LoginUtil.INSTANCE.e(TAG,"onLayout-after-2-");
    }

    @Override
    public void addView(View child) {
        LoginUtil.INSTANCE.e(TAG,"addView-before-1-");
        super.addView(child);
        LoginUtil.INSTANCE.e(TAG,"addView-after-2-");
    }
}
