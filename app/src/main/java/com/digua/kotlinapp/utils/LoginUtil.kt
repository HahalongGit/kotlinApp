package com.digua.kotlinapp.utils

import android.util.Log
import com.digua.kotlinapp.BuildConfig

fun <T>MutableList<T>.getOneData(){

}
/**
 * 日志输出
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
object LoginUtil {

    private val isDebug = BuildConfig.DEBUG



    fun e(TAG: String?, message: String?) {
        if (isDebug) {
            Log.e(TAG, message!!)
        }
    }

    fun d(TAG: String?, message: String?) {
        if (isDebug) {
            Log.d(TAG, message!!)
        }
    }
}