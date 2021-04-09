package com.digua.kotlinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.digua.kotlinapp.utils.LoginUtil;

import java.net.URL;

/**
 * 测试页面
 */
public class TestActivity extends AppCompatActivity {

    private static final String TAG = "Main-TestActivity--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LoginUtil.INSTANCE.e(TAG, "onCreate");
        Glide.with(this).load("").into(new ImageView(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        LoginUtil.INSTANCE.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginUtil.INSTANCE.e(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LoginUtil.INSTANCE.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LoginUtil.INSTANCE.e(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginUtil.INSTANCE.e(TAG, "onDestroy");
    }
}
