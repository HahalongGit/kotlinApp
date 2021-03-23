package com.digua.kotlinapp.main.api

import com.digua.kotlinapp.base.BaseResponse
import com.digua.kotlinapp.main.bean.ResultBean
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 首页api
 *
 * @author RunningDigua
 * @date 2021/3/15
 */
interface MainApi {

    /**
     * 普通Retrofit2
     * 请求
     */
    @GET("article/listproject/0/json")
    fun queryData(): Call<BaseResponse<ResultBean>>


    @GET("article/listproject/0/json")
    suspend fun queryDataWithKotlin1(): Call<BaseResponse<ResultBean>>

    /**
     * 采用kotlin 协程请求数据的方式
     */
    @GET("article/listproject/0/json")
    suspend fun queryDataWithKotlin(): BaseResponse<ResultBean>

    /**
     * 使用Rxjava请求的
     */
    @GET("article/listproject/0/json")
    fun queryDataWithRxjava(): Observable<BaseResponse<ResultBean>>

    /**
     * 采用Kotlin 协程方式请求数据（这接口有问题，请求不通）
     */
    @GET("toutiao/index")
    suspend fun queryOtherDataKotlin(@Query("type") type: String?, @Query("key") key: String?):BaseResponse<String>
}