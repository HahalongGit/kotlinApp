package com.digua.kotlinapp.test

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
interface ILogin<out T> {//out 表示这个类的泛型是只读的，其变量可以接受其他类型的对象

    fun login();

    fun register();

    fun nextT():T

}