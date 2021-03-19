package com.digua.kotlinapp.main.bean

/**
 *
 *
 * @author RunningDigua
 * @date 2021/3/19
 */
data class User(var name0: String = "") {

    //data 类为啥必须有一个primary 参数 的构造方法

    constructor(name: String = "",age:Int, weight:Float = 12.2f) : this(){
        this.name = name
        this.age = age
        this.weight = weight
    }

    var name: String? = name0
    var age = 0
    var weight = 0f

}