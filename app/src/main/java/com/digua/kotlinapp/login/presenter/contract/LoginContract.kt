package com.digua.kotlinapp.login.presenter.contract

import com.digua.kotlinapp.base.BasePresenter
import com.digua.kotlinapp.base.BaseView
import com.digua.kotlinapp.login.bean.LoginParam
import com.digua.kotlinapp.login.bean.LoginResult

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
interface LoginContract {

    interface LoginPresenter : BasePresenter {
        /**
         * 登录逻辑处理
         */
        fun login(loginParam: LoginParam)

    }

    interface LoginView : BaseView {

        fun setLoginResult(loginresult: LoginResult);

    }

}