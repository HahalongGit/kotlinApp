package com.digua.kotlinapp.login.presenter.contract

import com.digua.kotlinapp.base.BasePresenter
import com.digua.kotlinapp.base.BaseView
import com.digua.kotlinapp.base.IPresenter
import com.digua.kotlinapp.login.bean.LoginParam
import com.digua.kotlinapp.login.bean.LoginResult

/**
 * 登录业务的MVP 协议
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
interface LoginContract {

    interface LoginPresenter : IPresenter<LoginView> {//登录业务的Presenter继承了通用的IPresenter，传递业务层LoginView

        /**
         * 登录逻辑处理
         */
        fun login(loginParam: LoginParam)

    }

    interface LoginView : BaseView {

        fun setLoginResult(loginresult: LoginResult);

    }

}