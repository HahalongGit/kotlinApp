package com.digua.kotlinapp.main.fragment

import com.digua.kotlinapp.base.BasePresenter
import com.digua.kotlinapp.main.api.MainApi
import com.digua.kotlinapp.main.bean.GoodsInfo
import com.digua.kotlinapp.main.fragment.contract.MainFragmentContract
import com.digua.kotlinapp.main.presenter.MainPresenter
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @author RunningDigua
 * @date 2021/3/26
 */
class MianFragmentPresenter : BasePresenter<MainFragmentContract.MainFragmentView>(),
    MainFragmentContract.MainFragmentPresenter {

    private var mMainApi: MainApi

    private val mainScope = MainScope()

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        mMainApi = retrofit.create(MainApi::class.java)
    }

    override fun getGoodsInfoList(userId: String, pageIndex: Int) {
        GlobalScope.launch {

            val result = mMainApi.queryDataWithKotlin()
            println(TAG + "queryDataWithKotlin-success--code:" + result.code)
            println(TAG + "queryDataWithKotlin-success-data:" + Gson().toJson(result.data))
            mainScope.launch {
                result.data.let {
                    println(
                        TAG + "queryDataWithKotlin-success-it-data:" + it?.size
                    )
                    mView?.setGoodsData(mutableListOf<GoodsInfo>())
                }
            }

        }
    }

    companion object {
        private const val TAG = "MianFragmentPresenter"
    }


}