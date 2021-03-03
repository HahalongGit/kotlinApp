package com.digua.kotlinapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.digua.kotlinapp.base.BaseActivity;
import com.digua.kotlinapp.databinding.ActivitySplashBinding;
import com.digua.kotlinapp.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding mBinding;


    @NotNull
    @Override
    protected View getLayoutView() {
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {

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
        mBinding.btnGoMian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
