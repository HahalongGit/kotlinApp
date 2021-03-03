package com.digua.kotlinapp.test

/**
 * 函数参数和lambda表达式
 * 参考https://www.kotlincn.net/docs/reference/lambdas.html
 */
class FunctionParam{

    fun testString(){
        val list = listOf<String>("123","name","lili")
        list.filter{ it.contains("a") }.sortedBy { it }.map { it.toUpperCase() }
        //函数类型作为参数，通过lambda表达式进行了简化，通过对应方法的源码可以看到其内部函数作为参数的原型

        fun(x:Int,y:Int){//匿名函数，没有函数名，函数体可以是一个代码块或者一个表达式
            x+y
        }

    }

}