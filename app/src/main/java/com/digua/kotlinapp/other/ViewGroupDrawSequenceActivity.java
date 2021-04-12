package com.digua.kotlinapp.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.digua.kotlinapp.R;

/**
 * 测试ViewGroup中 draw、onDraw、dispatchDraw三个方法的绘制顺序
 *
 * 注意：对于draw和onDraw方法，在没有设置background的情况下不绘制执行，
 * dispatchDraw是viewGroup的默认绘制方法用来绘制子View
 *
 * 自定义了一个继承LinearLayout的ViewGroup 重写了上述三个方法测试
 */
public class ViewGroupDrawSequenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_view_group_draw_sequence);
    }
}
