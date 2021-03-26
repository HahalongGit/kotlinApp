package com.digua.kotlinapp.base;

import android.content.Context;
import android.os.Bundle;

import com.digua.kotlinapp.utils.LoginUtil;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author RunningDigua
 * @date 2021/3/26
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment {

    private static final String TAG = "BaseMvpFragment";

    protected T mPresenter;

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    private T initPresenter() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Class<T> tClass = (Class<T>) type.getActualTypeArguments()[0];
        try {
            return tClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
    }

}
