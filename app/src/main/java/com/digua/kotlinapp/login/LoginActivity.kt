package com.digua.kotlinapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.digua.kotlinapp.R
import com.digua.kotlinapp.base.BaseActivity
import com.digua.kotlinapp.login.bean.LoginResult
import com.digua.kotlinapp.login.presenter.LoginPresenter
import com.digua.kotlinapp.login.presenter.contract.LoginContract

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initPresenter() {
        mPresenter = LoginPresenter(this);
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun setLoginResult(loginresult: LoginResult) {
        TODO("Not yet implemented")
        //登录结果处理
    }

    override fun showMessage(message: String?) {
        super.showMessage(message)
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT)
        }
    }

}
