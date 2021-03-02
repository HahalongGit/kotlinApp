package com.digua.kotlinapp.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity<T :BasePresenter> : AppCompatActivity(), BaseView {

    protected var mPresenter:T? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initData()
        addListener()
    }


    protected abstract fun initView();

    protected abstract fun initPresenter();

    protected abstract fun initData();

    /**
     * 添加 View监听
     */
    protected open fun addListener() {
        //抽象类中的非抽象方法在外部访问需添加open修饰，默认是final类型的
    }

    override fun showLoading() {
        TODO("Not yet implemented")
        //Base中处理Loading的显示
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
        //Base中处理Loading的隐藏
    }

    override fun showMessage(message: String?) {
        TODO("Not yet implemented")
    }

}
