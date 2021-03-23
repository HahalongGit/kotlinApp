package com.digua.kotlinapp.login.presenter

import com.digua.kotlinapp.base.BasePresenter
import com.digua.kotlinapp.login.bean.LoginParam
import com.digua.kotlinapp.login.bean.LoginResult
import com.digua.kotlinapp.login.presenter.contract.LoginContract
import com.digua.kotlinapp.utils.LoginUtil

/**
 * 登录页面Presenter，继承通用的BasePresenter类，同时实现登录业务的LoginPresenter，传递业务的LoginView
 * LoginView的实现在 BaseMvpActivity 中初始化了Presenter之后调用 attachView方法传入
 *
 * 这里存在一点问题：Presenter没有在BasePresenter中初始化，因为BaseActivity是kotlin代码，没有办法采用反射的方式
 * 初始化Presenter（调用initPresenter()）
 *
 * 有关Kotlin中采用MVP架构的优化：
 * 由于Kotlin调用可以采用函数式参数，在Activity或者Fragment等View中直接写一个函数式调用可以设置RecycleView等的数据，
 * 所以对于Kotlin的MVP中的V可以优化不用写每个View的设置方法，可以直接在Activity或者Fragment中通过函数式参数设置RecycleView等
 *  presenter?.addReceiver(name, etMobile.text.toString(), etAccount.text.toString()) {
 *  presenter?.getAllReceiver{//函数式调用接口
 *          requestList(it) // 函数式调用接口直接隐式返回了“it”请求结果，requestList(it)就是给Activity的RecycleView设置数据
 *           dialog.dismiss()
 *      }
}
 * @author RunningDigua
 * @date 2021/3/2
 */
class LoginPresenter : BasePresenter<LoginContract.LoginView>(), LoginContract.LoginPresenter {

//    private lateinit var  mLoginView: LoginContract.LoginView //在Base中初始化mView,attachView中传递页面的View

    init {
        LoginUtil.e("LoginPresenter", "init初始化")
    }

    override fun login(loginParam: LoginParam) {
        var isSuccess: Boolean = true
        mView?.showLoading()
        if (isSuccess) {
            LoginUtil.e("LoginPresenter", "login-")
            //登录处理
            mView?.setLoginResult(LoginResult())
        } else {
            mView?.showMessage("登录失败~")
        }
        mView?.hideLoading()
    }


}