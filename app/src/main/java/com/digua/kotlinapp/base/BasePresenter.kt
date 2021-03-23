package com.digua.kotlinapp.base

/**
 * Mvp 模式的Base Presenter 处理一些公共的Presenter操作，业务的Presenter放在业务接口中
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
open class BasePresenter<V : BaseView> :IPresenter<V>{

    protected var mView:V? = null;

    override fun attachView(view: V) {
       mView = view;
    }

    override fun onDestroy() {
       mView = null
    }


}