package com.digua.kotlinapp.main.presenter

import com.digua.kotlinapp.base.BaseResponse
import com.digua.kotlinapp.main.api.MainApi
import com.digua.kotlinapp.main.bean.ResultBean
import com.digua.kotlinapp.main.presenter.contract.MainContract
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main类的实现
 *
 * @author RunningDigua
 * @date 2021/3/15
 */
class MainPresenter constructor(veiw: MainContract.MainView) : MainContract.MainPresenter {

    private var mMainView: MainContract.MainView = veiw;

    private var mMainApi: MainApi

    companion object {
        val TAG = MainPresenter.javaClass.name + "......"
    }

    init {
        println(TAG + "init")
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create()) //返回结果直可以接转换成json
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rxjava2中使用的转换器
            .build()
        mMainApi = retrofit.create(MainApi::class.java)
    }

    /**
     * 采用Retroift2 普通的调用，返回Call类型
     */
    override fun queryData(param: String) {
        print(TAG + param)
        mMainApi.queryData()?.enqueue(object : Callback<BaseResponse<ResultBean>> {
            override fun onResponse(
                call: Call<BaseResponse<ResultBean>>,
                response: Response<BaseResponse<ResultBean>>
            ) {
                println(TAG + "queryData-isSuccess:" + response.isSuccessful)
                println(TAG + "queryData-body--" + Gson().toJson(response).toString())
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call: Call<BaseResponse<ResultBean>>, t: Throwable) {
                print(TAG + t.message)
            }
        })

    }

    /**
     * 采用kotlin 协程 返回 Retroift2 的Call<T>类型 测试
     */
    override fun queryDataWithKotlin1() {
        TODO("这中方式 添加协程 请求 返回Call报错")
        println(TAG+"queryDataUse-协程--start-")
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val call:Call<BaseResponse<ResultBean>> = mMainApi.queryDataWithKotlin1()
                call.enqueue(object : Callback<BaseResponse<ResultBean>> {
                    override fun onResponse(
                        call: Call<BaseResponse<ResultBean>>,
                        response: Response<BaseResponse<ResultBean>>
                    ) {
                        println(TAG + "queryDataUse-协程-isSuccess:" + response.isSuccessful)
                        println(TAG + "queryDataUse-协程-boty-" + Gson().toJson(response).toString())
                        if (response.isSuccessful) {

                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<ResultBean>>, t: Throwable) {
                        print(TAG + t.message)
                    }
                })

            }catch (e:Exception){
                println(TAG+"queryDataUse-协程-error:"+e.toString())
            }
        }
        println(TAG+"queryDataUse-协程--end-")
    }

    /**
     * 采用kotlin 协程 配合Retroift2.6 （2.6以上支持kotlin协程） 请求接口，直接放回数据bean
     *
     */
    override fun queryDataWithKotlin(param: String) {
        println(TAG+"queryDataWithKotlin--start-")
        GlobalScope.launch(Dispatchers.Main) {
            try {
                var result:BaseResponse<ResultBean> = mMainApi.queryDataWithKotlin()
                println(TAG+"queryDataWithKotlin-success--code:"+result.code)
                println(TAG+"queryDataWithKotlin-success-data:"+Gson().toJson(result.data))
            }catch (e:Exception){
                println(TAG+"queryDataWithKotlin-error:"+e.toString())
            }
        }
        println(TAG+"queryDataWithKotlin--end-")
    }

    /**
     * 采用Rextrofit +rxjava普通调用模式
     */
    override fun queryDataWithRxjava(param: String) {
        mMainApi.queryDataWithRxjava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                println(TAG+"rxjava-"+it.code)
                val result: ResultBean? = it.data //采用了rxjava2之后直接转换为Bean
                println(TAG+"rxjava-size:"+result?.size)
            })
    }


    override fun onDestroy() {

    }

}