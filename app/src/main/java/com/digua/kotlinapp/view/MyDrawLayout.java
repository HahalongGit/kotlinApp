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
 *
 * 2.在dispatchDraw中实现QQ消息的气泡拖拽效果
 * 思路：
 * 1.在屏幕上设置一个初始位置，绘制一个圆
 * 2.手指在屏幕上按下时绘制一个圆，跟随手指移动
 * 3.根据QQ气泡的效果，观察到两个圆之间的连接是贝塞尔曲线，根据贝塞尔曲线的绘制我们知道要找到一个控制点，
 * 使用PS的钢笔工具模拟气泡的拖拽效果（钢笔工具的绘制的效果就是贝塞尔曲线效果），发现控制点就是两个圆圆心的连线的中点。
 * 4.分析QQ气泡的拖拽效果图发现，连接点在两个圆的四个切点上，因此需要知道四个切点的位置，并根据位置计算出圆心连线的中点（控制点）
 * 5.得到圆的四个切点和中线的控制点后，创建一个Path，调用
 *         mPath.moveTo(mStartPoint.x, mStartPoint.y);
 *         mPath.lineTo(x1, y1);
 *         mPath.quadTo(anchorX, anchorY, x2, y2);
 *         mPath.lineTo(x3, y3);
 *         mPath.quadTo(anchorX, anchorY, x4, y4);
 *         mPath.lineTo(x1, y1);
 *         设置Path
 * 6.dispatchDraw中绘制圆 同时根据按下的状态绘制 Path贝塞尔曲线
 * 7.根据业务addView 一个TextView作为初始的消息气泡，当手指拖动时在TextView位置绘制一个圆，让TextView的气泡跟随手指移动
 *   获取一个View的左上角的坐标的方法：
 *   int location[] = new int[2];// 获取坐标并放进数组中
 *   mTextView.getLocationOnScreen(location);//获取TextView的左上角的位置
 *   根据数组计算出手指移动的时候TextView的中心在当前点
 * 8.根据滑动的距离设置大于指定距离时脱离初始点，并且在这个范围外手指释放后添加爆炸效果
 * 9.爆炸效果：添加爆炸状态图片，设置一个帧动画，在手指释放的时候执行动画
 *
 * 10.手指滑动的分析：
 *    由于手机上的坐标系右边为正，下边为正，因此做此类分析的时候以正方向为主，方便分析。
 *    由于三角函数的关系，在坐标系中当X轴为负值，或者Y轴为负值的时候得到函数关系：
 *    sin(-a) = - sina;
 *    cos(-a) = cosa;
 *    sin(π/2-α) = cosα
 *    cos(π/2-α) = sinα
 *    当角度的值是负值的时候
 *    x = x0 + r * sin（-a）;
 *    y = y0 - r * cosa;
 *    因此，也就变成了下面的公式了：
 *    x = x0 - r * sina;
 *    y = y0 - r * cosa;
 *   所以对于上面在坐标系正向的分析也就满足其他坐标的计算
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
        //ViewGroup中draw和onDraw只有在设置了background的时候才执行绘制
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
