package com.digua.kotlinapp

import com.digua.kotlinapp.login.bean.LoginParam
import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object{
        val TAG = ExampleUnitTest.javaClass.name+"---"
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testParam(){
        val param:LoginParam = LoginParam()
        print("Log输出")
    }

    @Test
    fun testCorountines(){
        GlobalScope.launch {
            println("start------------")
            for ( i in 1..3){
                delay(330)
                println("i = $i --> "+ System.currentTimeMillis())
            }
            println(TAG+"--thread-1--"+Thread.currentThread().name)
            println("world ")
            test()
        }
        println("hello")
        Thread.sleep(3000)  //// 阻塞主线程 3 秒钟来保证 JVM 存活 是什么意思？ 注释掉就launch就不执行了
        println(TAG+"--thread-2--"+Thread.currentThread().name);
    }

    private suspend fun test(){
        println(TAG+"thread-test-1"+Thread.currentThread().name);
        withContext(Dispatchers.IO){
            println(TAG+"thread-test-2"+Thread.currentThread().name);
        }
    }

}
