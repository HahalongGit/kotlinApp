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
class LoginPresenter constructor(loginView: LoginContract.LoginView) : LoginContract.LoginPresenter {



    var mLoginView: LoginContract.LoginView? = null

    init {
        mLoginView = loginView;
        LoginUtil.e("TAG","init初始化")
    }
//    constructor(loginView: LoginContract.LoginView) : this() {
//        mLoginView = loginView;
//    }

    override fun login(loginParam: LoginParam) {
        TODO("登录处理")
        mLoginView?.setLoginResult(LoginResult())
    }

    override fun onDestroy() {
        mLoginView = null
    }


}