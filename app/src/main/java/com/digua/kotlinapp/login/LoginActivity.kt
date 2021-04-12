package com.digua.kotlinapp.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.digua.kotlinapp.main.MainActivity
import com.digua.kotlinapp.base.BaseActivity
import com.digua.kotlinapp.basemvp.BaseMvpActivity
import com.digua.kotlinapp.databinding.AppActivityLoginBinding
import com.digua.kotlinapp.login.bean.LoginParam
import com.digua.kotlinapp.login.bean.LoginResult
import com.digua.kotlinapp.login.presenter.LoginPresenter
import com.digua.kotlinapp.login.presenter.contract.LoginContract
import com.digua.kotlinapp.utils.LoginUtil
import com.digua.kotlinapp.utils.SharePreferencesTools

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
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.LoginView {

    companion object {
        //定义伴随对象
        private const val TAG: String = "LoginActivity"
    }

    private lateinit var mBinding: AppActivityLoginBinding;
    //lateInit 延迟初始化的时机,这样不用再初始化时赋值null，
    // 未初始化之前访问会抛出异常UninitializedPropertyAccessException

    private lateinit var mSharedPreferences: SharePreferencesTools

    override fun getLayoutView(): View {
        mBinding = AppActivityLoginBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun initView() {
        LoginUtil.e(TAG, "initView-")

        mSharedPreferences = SharePreferencesTools(this)
        mSharedPreferences.putUserId("digua_123456789")
    }


    override fun addListener() {
        super.addListener()
        login()
    }

    private fun login() {
        mBinding.btnLogin.setOnClickListener {
            var loginParam = LoginParam()
            loginParam.password = "12331"
            mPresenter?.login(loginParam)
        }
    }


    override fun initData() {

    }

    override fun setLoginResult(loginresult: LoginResult) {
        //登录结果处理
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
        var userId = mSharedPreferences.getUserId()
        LoginUtil.e(TAG, "setLoginResult-userId:$userId")

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)

    }


}
