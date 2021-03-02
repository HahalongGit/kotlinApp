package com.digua.kotlinapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.digua.kotlinapp.base.BaseActivity;
import com.digua.kotlinapp.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void initView() {

        mBinding.btnGoMian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {
        super.addListener();
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }
}
