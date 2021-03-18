package com.digua.kotlinapp

import org.junit.Test

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/18
 */
class KotlinExampleUnitTest {

    @Test
    fun foo() {
        println("定义一个方法")
        DataProviderManager.registerDataProvider()
        DataProviderManager.manager()
        DataProviderManager.getUserInfo()
        println("DataProviderManager.name:" + DataProviderManager.name)
    }

    @Test
    fun testCompanion() {
        val myClass: MyClass.Companion = MyClass
        myClass.manager()
    }

    @Test
    fun testLoin() {
        LoginMananger.login()
    }

    /**
     * 声明一个MutableList类的扩展函数swap
     */
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    @Test
    fun testKuoZhanFunction() {
        //创建一个MutableList 的集合
        val list = mutableListOf(1, 3, 4)
        println(list) //输出结果：[1, 3, 4]
        list.swap(0, 2) //调用扩展函数
        println(list)//调用swap()后输出结果：[4, 3, 1]
    }

}

// 只有一个抽象方法，和若干非抽象方法的接口叫函数式接口
interface ILogin {

    fun login(username: String, password: String)
}

object LoginMananger {
    // 直接使用接口创建一个接口对象的实例
    private val loginPresenter = object : ILogin {
        override fun login(username: String, password: String) {
            println("正在登陆...")
        }

    }


    fun login() {//直接调用接口对象的方法
        loginPresenter.login("lili", "123456")
    }

}

interface Manager {

    val name: String

    fun manager()
    fun getUserInfo() {
        println("接口中定义的方法")
    }
}

//对象声明
object DataProviderManager : Manager {


    fun registerDataProvider() {
        println("对象声明")

    }

    override val name: String
        get() = "longlong.liu"

    override fun manager() {
        println("对象声明中，父类的实现方法")
    }

    override fun getUserInfo() {
        super.getUserInfo()
        println("接口中的重写的实现方法")
    }

}

class MyClass {
    companion object : Manager {
        override val name: String //重写父类的属性name
            get() = "longlong.liu"

        override fun manager() {
            println("伴生对象实现的接口")
        }

    }
}

