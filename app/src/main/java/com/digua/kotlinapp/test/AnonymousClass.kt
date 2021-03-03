package com.digua.kotlinapp.test

/**
 *
 * @author RunningDigua
 * @date 2021/3/3
 */
class AnonymousClass {

    private fun foo() = object {//私有函数，所以其返回值类型是匿名对象类型
        val x:String = "xyz"
    }

    fun publicFoo() = object { // 共有函数，所以返回值类型是Any
        val x:String = "abc"
    }

    fun testFoo(){
        val result = foo().x // 没有问题
//        val result2 = publicFoo().x // 错误，不能解析的x
    }

}