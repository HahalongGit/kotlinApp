package com.digua.kotlinapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.digua.kotlinapp.R
import com.digua.kotlinapp.base.BaseActivity
import com.digua.kotlinapp.databinding.ActivityLoginBinding
import com.digua.kotlinapp.login.bean.LoginParam
import com.digua.kotlinapp.login.bean.LoginResult
import com.digua.kotlinapp.login.presenter.LoginPresenter
import com.digua.kotlinapp.login.presenter.contract.LoginContract
import com.digua.kotlinapp.utils.LoginUtil

/**
 *登录模块
 * RxBinding
 * RxBus--RxJava EventBus、RxBux都不推荐
 * viewBinding 绑定View
 * dataBinding 绑定data
 * Kotlin 有插件支持 apply plugin: 'kotlin-android-extensions'
 * Kotlin代码中使用这个插件，布局中写了id的View自动生成View对象
 *
 * https://www.yisu.com/zixun/203464.html 使用viewBinding的优势！比Kotlin插件的优势，Kotln和java都可以用
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.LoginView {

    companion object{//定义伴随对象
        private const val TAG:String = "LoginActivity"
    }

    private lateinit var mBinding: ActivityLoginBinding ;
    //lateInit 延迟初始化的时机,这样不用再初始化时赋值null，
    // 未初始化之前访问会抛出异常UninitializedPropertyAccessException

    override fun getLayoutView(): View {
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        return mBinding?.root
    }

    override fun initView() {

    }

    override fun initPresenter() {
        LoginUtil.e(TAG,"initPresenter-Before")
        super.addListener()
        LoginUtil.e(TAG,"initPresenter-after")
        mPresenter = LoginPresenter(this);
    }

    override fun addListener() {
        super.addListener()
        LoginUtil.e(TAG,"addListener-Login")
        mBinding.btnLogin?.setOnClickListener {
            LoginUtil.e(TAG,"addListener-click-Login")
            mPresenter?.login(LoginParam())
        }

    }

    override fun initData() {

    }

    override fun setLoginResult(loginresult: LoginResult) {
        //登录结果处理
        LoginUtil.e(TAG,"setLoginResult-登录成功")
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String?) {
        super.showMessage(message)
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT)
        }
    }



}
