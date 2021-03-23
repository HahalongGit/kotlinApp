package com.digua.kotlinapp.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.digua.kotlinapp.utils.LoginUtil

abstract class BaseActivity: AppCompatActivity(), BaseView {

    companion object {
        private const val TAG: String = "BaseActivity";
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginUtil.e(TAG, "onCreate")
        setContentView(getLayoutView())
        initView()
        initData()
        addListener()
    }

    override fun onStart() {
        super.onStart()
        LoginUtil.e(TAG, "onStart")
    }

    protected abstract fun getLayoutView(): View

    protected abstract fun initView();



    protected abstract fun initData();

    /**
     * 添加 View监听
     */
    protected open fun addListener() {
        //抽象类中的非抽象方法在外部访问需添加open修饰，默认是final类型的
    }

    override fun showLoading() {
        //Base中处理Loading的显示
    }

    override fun hideLoading() {
        //Base中处理Loading的隐藏
    }

    override fun showMessage(message: String?) {
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

}
