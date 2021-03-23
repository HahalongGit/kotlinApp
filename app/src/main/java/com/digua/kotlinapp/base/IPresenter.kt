package com.digua.kotlinapp.base

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/23
 */
interface IPresenter<T>{

    fun attachView(view: T)

    fun onDestroy();

}