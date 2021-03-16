package com.digua.kotlinapp.main.presenter

import android.widget.Toast
import com.digua.kotlinapp.base.BaseResponse
import com.digua.kotlinapp.main.api.MainApi
import com.digua.kotlinapp.main.bean.ResultBean
import com.digua.kotlinapp.main.presenter.contract.MainContract
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.*
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
        println(TAG + "queryData-currentThread1:" + Thread.currentThread().name)  //Thread --main
        mMainApi.queryData()
            ?.enqueue(object : Callback<BaseResponse<ResultBean>> {
            override fun onResponse(
                call: Call<BaseResponse<ResultBean>>,
                response: Response<BaseResponse<ResultBean>>
            ) {
                println(TAG + "queryData-currentThread2:" + Thread.currentThread().name) //Thread --main
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
     * 采用kotlin 协程 挂起函数 返回 Retroift2 的Call<T>类型 测试
     */
    override fun queryDataWithKotlin1() {
        println(TAG + "queryDataUse-currentThread1:" + Thread.currentThread().name) //main Thread
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val call:Call<BaseResponse<ResultBean>> = mMainApi.queryDataWithKotlin1()
                call.enqueue(object : Callback<BaseResponse<ResultBean>> {
                    override fun onResponse(
                        call: Call<BaseResponse<ResultBean>>,
                        response: Response<BaseResponse<ResultBean>>
                    ) {
                        ////Thread -main
                        println(TAG + "queryDataUse-currentThread2:" + Thread.currentThread().name)
//                        调用报错
//                        println(TAG + "queryDataUse-协程-isSuccess:" + response.isSuccessful)
//                        println(TAG + "queryDataUse-协程-boty-" + Gson().toJson(response).toString())
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
        println(TAG+"queryDataWithKotlin-currentThread1:"+Thread.currentThread().name) //Thread-main
        println(TAG+"queryDataWithKotlin--start-")
        GlobalScope.launch(Dispatchers.IO) {//Dispatchers.IO指定切换到IO线程
            // suspend修饰的挂起函数在执行时会自动切换线程，要切换到哪里需要我们指定,指定为IO线程后获取线程名
            // 输出为：queryDataWithKotlin-currentThread2:DefaultDispatcher-worker-1
            try {
                var result:BaseResponse<ResultBean> = mMainApi.queryDataWithKotlin()
                println(TAG+"queryDataWithKotlin-currentThread2:"+Thread.currentThread().name)// Thread-main
                println(TAG+"queryDataWithKotlin-success--code:"+result.code)
                println(TAG+"queryDataWithKotlin-success-data:"+Gson().toJson(result.data))
                result.data?.let { mMainView?.setResult(it) } //这里设置的时候线程没有切换回来还是 DefaultDispatcher-worker-1
            }catch (e:Exception){
                println(TAG+"queryDataWithKotlin-error:"+e.toString())
            }
        }
        println(TAG+"queryDataWithKotlin--end-"+Thread.currentThread()) //end-Thread[main,5,main]
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



    override fun testSuspend() {

        println(TAG+"testSuspend-currentThread1:"+Thread.currentThread().name) //currentThread1:main
        runBlocking {
//            delay(10000) //runBlocking delay 会阻塞主线程
            println(TAG+"testSuspend-currentThread2:"+Thread.currentThread().name) //currentThread2:main
            GlobalScope.launch {
                println(TAG+"testSuspend-currentThread3:"+Thread.currentThread().name) //DefaultDispatcher-worker-2
                suspendFunction()
            }
        }

    }

    /**
     * 如何定义一个 suspend函数 suspend必须运行在协程中 或者另外一个suspend函数中。
     */
    private suspend fun suspendFunction() {// withContext(Dispatchers.IO) 添加一个协程的线程切换
        //suspend 修饰的挂起函数 必须运行在协程或者另外一个suspend修饰的函数中:
        //其实所谓挂起就是这里对执行的代码进行一个线程的切换，一般只有耗时的、等待的操作我们才
        //申明为suspend，（相当于Android中耗时操作要运行在非主线程一样）suspend本身能够切换到
        //其他线程，同时在suspend执行完毕还能够切换回执行suspend之前的线程。

        //DefaultDispatcher-worker-2
        delay(3000) //添加上delay 本方法中的 suspend才生效
        println(TAG+"testSuspend-currentThread4:"+Thread.currentThread().name)

    }

    override fun onDestroy() {

    }

}