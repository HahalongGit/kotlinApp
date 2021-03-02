package com.digua.kotlinapp.base;

/**
 * MVP的Base View
 * Kotlin 中接口不能继承 智能实现
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
public  interface BaseView {

    /**
     * 显示加载进度
     */
    public abstract void showLoading();

    /**
     * 隐藏加载进度
     */
    public abstract void hideLoading();

    /**
     * 显示提示
     * @param message
     */
    public abstract void showMessage(String message);

}
