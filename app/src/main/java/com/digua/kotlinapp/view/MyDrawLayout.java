package com.digua.kotlinapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.digua.kotlinapp.R;
import com.digua.kotlinapp.utils.LoginUtil;

/**
 * 重写draw、onDraw、dispatchDraw三个方法测试绘制顺序
 * 日志输出如下：
 * <p>
 * E/MyLinearLayout: onLayout-before-1-
 * MyLinearLayout: onLayout-after-2-
 * <p>
 * MyLinearLayout: draw-before-1-
 * MyLinearLayout: onDraw-before-1-
 * MyLinearLayout: onDraw-after-2-
 * MyLinearLayout: dispatchDraw-before-1-
 * MyLinearLayout: dispatchDraw-after-2-
 * MyLinearLayout: draw-after-2-
 * <p>
 * 由此可知：draw 方法的在super.draw之前的代码最先执行，然后是onDraw方法执行（super.onDraw前后的代码顺序执行）
 * 接着是dispatchDraw方法中dispatchDraw前后的代码顺序执行，最后，draw的super.draw之后的代码才执行。
 *
 * @author RunningDigua
 * @date 2021/3/29
 */
public class MyDrawLayout extends FrameLayout {

    private Paint mPaint;

    private static final String TAG = "MyLinearLayout";

    private int DEFAULT_RADIUS = 30;

    private int mRadius = 30;

    private Point mCurrentPoint, mStartPoint;

    private Path mPath;

    private boolean isTouch;

    /**
     * 最小半径8
     */
    private int mMinRadius = 8;

    private TextView mTextView;

    public MyDrawLayout(Context context) {
        this(context, null);
    }

    public MyDrawLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#973311"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mCurrentPoint = new Point();
        mStartPoint = new Point(200,200);//初始位置
        mPath = new Path();

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 300;
        layoutParams.leftMargin = 300;
        mTextView = new TextView(context);
        mTextView.setLayoutParams(layoutParams);
        mTextView.setBackgroundResource(R.drawable.bg_text_shape);
        mTextView.setPadding(10, 10, 10, 10);
        mTextView.setText("99+");
        mTextView.setTextColor(Color.parseColor("#ffffff"));
        addView(mTextView);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void draw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG, "draw-before-1-");
        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 240, mPaint);
        mPaint.setTextSize(38);
        String text = "我是一个纯绿色的圆";
        mPaint.setColor(Color.parseColor("#ffffff"));//设置字体颜色
        canvas.drawText(text, 0, text.length(), (getWidth() - 240) / 2, getHeight() / 2, mPaint);
        super.draw(canvas);
        LoginUtil.INSTANCE.e(TAG, "draw-after-2-");
    }

    @Override
    public void setBackgroundColor(int color) {
        LoginUtil.INSTANCE.e(TAG, "setBackgroundColor-before-1-" + color);
        super.setBackgroundColor(color);
        LoginUtil.INSTANCE.e(TAG, "setBackgroundColor-after-2-" + color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG, "onDraw-before-1-");
        mPaint.setColor(Color.parseColor("#ff0012"));
        canvas.drawCircle(getWidth() / 3, getHeight() / 3, mRadius, mPaint);
        super.onDraw(canvas);
        LoginUtil.INSTANCE.e(TAG, "onDraw-after-2-");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        LoginUtil.INSTANCE.e(TAG, "dispatchDraw-before-1-");
        mPaint.setColor(Color.parseColor("#FF0000"));


        if (isTouch) {
            catulatePath();
            canvas.drawCircle(mStartPoint.x, mStartPoint.x, mRadius, mPaint);
            canvas.drawCircle(mCurrentPoint.x, mCurrentPoint.y, mRadius, mPaint);
            canvas.drawPath(mPath, mPaint);
            mTextView.setX(mCurrentPoint.x - mTextView.getWidth() / 2);
            mTextView.setY(mCurrentPoint.y - mTextView.getHeight() / 2);
        } else {
            //设置TextView中心在当前手指的位置
            mTextView.setX(mStartPoint.x - mTextView.getWidth() / 2);
            mTextView.setY(mStartPoint.y - mTextView.getHeight() / 2);
//                canvas.drawCircle(mCurrentPoint.x, mCurrentPoint.y, mRadius, mPaint);//测试 TextView是不是在圆心
        }

        super.dispatchDraw(canvas);
        LoginUtil.INSTANCE.e(TAG, "dispatchDraw-after-2-");
    }

    private void catulatePath() {
        float x = mCurrentPoint.x;
        float y = mCurrentPoint.y;
        float startX = mStartPoint.x;
        float startY = mStartPoint.y;
        float dx = x - startX;
        float dy = y - startY;
        //求夹角
        double a = Math.atan(dy / dx);
        //夹角对应的半径的值
        float offsetX = (float) (mRadius * Math.sin(a));
        float offsetY = (float) (mRadius * Math.cos(a));

        // 根据角度算出四边形的四个点(两个圆上的四个切点)
        float x1 = startX - offsetX;
        float y1 = startY + offsetY;

        float x2 = x - offsetX;
        float y2 = y + offsetY;

        float x3 = x + offsetX;
        float y3 = y - offsetY;

        float x4 = startX + offsetX;
        float y4 = startY - offsetY;

        //计算出两个圆心连线的中点，作为贝塞尔曲线的控制点
        float anchorX = (startX + x) / 2;
        float anchorY = (startY + y) / 2;

        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.lineTo(x1, y1);
        mPath.quadTo(anchorX, anchorY, x2, y2);
        mPath.lineTo(x3, y3);
        mPath.quadTo(anchorX, anchorY, x4, y4);
        mPath.lineTo(x1, y1);

        int distance = (int) Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));//两个圆心之间的距离
        mRadius = DEFAULT_RADIUS - distance / 16;
        if (mRadius < mMinRadius) {
            mRadius = mMinRadius;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Rect rect = new Rect();
                int location[] = new int[2];
                mTextView.getLocationOnScreen(location);//获取TextView的左上角的位置
                rect.left = location[0];
                rect.top = location[1];
                rect.right = location[0] + mTextView.getWidth();
                rect.bottom = location[1] + mTextView.getHeight();

                if (rect.contains((int) event.getRawX(), (int) event.getRawY())) {//触摸点是否在TextView内部
                    isTouch = true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                isTouch = false;
                break;
            }
        }
        mCurrentPoint.x = (int) event.getX();
        mCurrentPoint.y = (int) event.getY();

        postInvalidate();
        return true;
    }
}
