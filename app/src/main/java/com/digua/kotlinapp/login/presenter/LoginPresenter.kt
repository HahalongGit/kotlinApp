package com.digua.kotlinapp.login.presenter

import android.util.Log
import com.digua.kotlinapp.login.LoginActivity
import com.digua.kotlinapp.login.bean.LoginParam
import com.digua.kotlinapp.login.bean.LoginResult
import com.digua.kotlinapp.login.presenter.contract.LoginContract
import com.digua.kotlinapp.utils.LoginUtil

/**
 * 登录页面Presenter
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
class LoginPresenter constructor(loginView: LoginContract.LoginView) :
    LoginContract.LoginPresenter {

    private var mLoginView: LoginContract.LoginView = loginView

    init {
        LoginUtil.e("LoginPresenter", "init初始化")
    }

    override fun login(loginParam: LoginParam) {
        var isSuccess: Boolean = true
        mLoginView?.showLoading()
        if (isSuccess) {
            LoginUtil.e("LoginPresenter", "login-")
            //登录处理
            mLoginView?.setLoginResult(LoginResult())
        } else {
            mLoginView?.showMessage("登录失败~")
        }
        mLoginView?.hideLoading()
    }

    override fun onDestroy() {
//        mLoginView = null
        //如何释放view对象
    }


}