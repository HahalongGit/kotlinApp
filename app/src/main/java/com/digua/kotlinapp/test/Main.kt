package com.digua.kotlinapp.test

/**
 * Kotlin 测试
 *
 * @author RunningDigua
 * @date 2021/3/1
 */
class Main {

    fun main() {
        println("你好，kotlin")
    }

    /**
     * Kotlin 参数定义 参数在前面，类型在后面
     */
    fun sum(a: Int, b: Int): Int {
        return a + b;
    }

    //返回值类型自动推断的函数
    fun sun(a: Int, b: Int) = a + b

    //返回无意义的值，Unit可以省略
    fun printSum(a: Int, b: Int): Unit {

    }

    fun printSum1(a: Int, b: Int) {

    }

    //定义只读局部变量使用关键字val定义，智能为其赋值一次

    val a: Int = 1// 立即赋值，申明了类型的

    val b = 2 // 变量没有类型，自动推断出 Int 类型
//    val c: Int     的、变量必须申明成抽象的或者赋初值
//    c = 3;

    // 可以重新赋值的变量用var定义
    var x = 5 //自动推断出类型是Int

    fun a() {
//        b = 12 //val定义的重新赋值报错
        x = 12 // var 定义的类型才可以重新赋值，
    }

    //当某个变量的值可以为null的时候，必须在申明处的类型后边添加？来标识引用可以为空
    fun parseInt(str: String): Int? {
        //如果 str 的内容不是数字返回 null

        return null;
    }

    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)  // Kotlin中如何把String转换为其他类型的数据，Int double类型
        val y = parseInt(arg2)
        if (x != null && y != null) {
            val result = x * y //直接使用 x * y 值会导致编译报错，因为他们可能为null
        } else {
            print("'$arg1' or '$arg2' is not a number")
        }

    }

    val items = listOf("apply","2342","kotlin")

    fun print(): Unit {
        //使用lambad 表达式来过滤filter 与映射
        items.filter { it.startsWith("a")}
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach{(println(it))}

        var index = 0
        while (index<items.size){
            println("item at $index is ${items[index]}")
            index++
        }
    }

    /**
     * 1.out 测试申明处变化
     */
    fun testOut(strs:Student<String>){
        val objects:Student<Any> = strs //此处把Student<String>的strs 赋值给Studnet<Any>，
        var ss:Any =  objects.nextStudent() //此处返回的结果不能被使用
        //因为提前申明了Student泛型的out属性，因此代码不会报错

    }

    //2.型变注释in，它使得一个类型参数逆变，只可被消费而不可以被生产。逆变类型的一个很好的例子是Comparable：
    interface Comparable<in T>{ //这里可以理解 in的意思是作为参数传递进入
        operator fun compareTo(other:T):Int
    }

    fun testInDemo(x:Comparable<Number>){
        x.compareTo(1.0)//1.0是double类型，它是Number的子类型
        //因此，我们可以讲x赋值给类型Comparable<Double>的变量
        val y:Comparable<Double> = x // OK 因为Double是Number的子类
    }

    fun testOuter(){
        val outer1 = Outer.MyInnerClass().foo() //访问嵌套类，MyInnerClass相当于java的静态内部类
        val innerDemo = Outer().Inner().getUserInfo()
        //内部类需要外部类的对象调用创建对象，内部类持有外部类的一个引用

        //kotlin中的匿名内部类
        val outer = Outer()
        outer.setConClickListener(object:Outer.OnClickListener{
            override fun onClick() {
                //java 中的匿名内部类采用 object申明
            }

            override fun onLongClick(position: Int) {
                //java 中的匿名内部类采用 object申明
            }
        })

    }


    fun testAnonymousClass(){
        val anonymousClass:AnonymousClass = AnonymousClass();
        anonymousClass.publicFoo()
    }


    //委托实现
    //委托实现的作用是什么？

    interface Base {
        fun print()
    }
    class BaseImpl(val x: Int) : Base {
        override fun print() { print(x) }
    }
    class Derived(base: Base) : Base by base{
        override fun print() {
            //可以重写其方法
        }
    }

    fun main1() {
        val b = BaseImpl(10)
        Derived(b).print()
    }
}