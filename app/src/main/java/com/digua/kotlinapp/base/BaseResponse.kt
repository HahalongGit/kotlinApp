package com.digua.kotlinapp.base

/**
 * BaseResposne
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
class BaseResponse<T> {

    var code = 0
    var message: String? = null
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }
}