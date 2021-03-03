package com.digua.kotlinapp.test

/**
 *
 *
 * @author RunningDigua
 * @date 2021/3/3
 */
interface Student<out T> {//out 表示这个类的泛型是只读的，其变量可以接受其他类型的对象

    fun nextStudent():T

}