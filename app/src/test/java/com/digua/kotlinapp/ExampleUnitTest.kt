package com.digua.kotlinapp

import com.digua.kotlinapp.login.bean.LoginParam
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testParam(){
        val param:LoginParam = LoginParam()
        print("Log输出")
    }


}
