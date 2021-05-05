package com.digua.kotlinapp

fun List<String>.getLongestString():String{
    println("getLongestString")
    return "这是longest的返回值"
}

fun <T>MutableList<T>.getSize(){

    size
}

/**
 * 扩展函数定义的类
 *
 * @author RunningDigua
 * @date 2021/3/19
 */
class Extension<T>{

}