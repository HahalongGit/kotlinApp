package com.digua.kotlinapp.basemvp;

import android.os.Bundle;

import com.digua.kotlinapp.base.BaseActivity;
import com.digua.kotlinapp.base.BasePresenter;
import com.digua.kotlinapp.utils.LoginUtil;

import org.jetbrains.annotations.Nullable;
import org.litepal.util.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity {

    protected T mPresenter;

    private static final String TAG = "BaseMvpActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();//让Presenter早于BaseActivity的初始化执行
        super.onCreate(savedInstanceState);

        //Base中 初始化View
        mPresenter.attachView(this);
    }

    //这个方法在子类的Activity被调用，所以子类 getGenericSuperclass得到就是BaseMvpActivity<T extends BasePresenter>
    protected T initPresenter(){
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Class<T> tClass = (Class<T>) type.getActualTypeArguments()[0];
        try {
            return tClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

}
