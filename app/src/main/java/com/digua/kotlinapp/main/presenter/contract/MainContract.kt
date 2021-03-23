package com.digua.kotlinapp.main.presenter.contract

import com.digua.kotlinapp.base.BasePresenter
import com.digua.kotlinapp.base.BaseView
import com.digua.kotlinapp.base.IPresenter
import com.digua.kotlinapp.main.bean.ResultBean

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/15
 */
interface MainContract {

    interface MainPresenter : IPresenter<MainView> {

        fun queryData(param: String)

        /**
         * 采用kotlin 协程 返回 Retroift2 的Call<T>类型
         */
        fun queryDataWithKotlin1()

        fun queryDataWithKotlin(param: String)

        fun queryDataWithRxjava(param: String);

        /**
         * 函数式调用接口（相当于设置了一个回调）
         */
        fun queryDataWithKotlinFunctionParam(param: String,callkack:(result:ResultBean)->Unit)

//        fun queryOtherDataKotlin(type: String?, key: String?)

        fun testSuspend();

    }


    interface MainView : BaseView {
        fun setResult(result: ResultBean);
    }


}