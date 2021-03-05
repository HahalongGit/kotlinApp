package com.digua.kotlinapp.test

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlin.system.measureTimeMillis

/**
 * 函数参数和lambda表达式
 * 参考https://www.kotlincn.net/docs/reference/lambdas.html
 */
class FunctionParam {

    fun testString() {
        val list = listOf<String>("123", "name", "lili")
        list.filter { it.contains("a") }.sortedBy { it }.map { it.toUpperCase() }
        //函数类型作为参数，通过lambda表达式进行了简化，通过对应方法的源码可以看到其内部函数作为参数的原型
        //lambda表达式总是在花括号中， 完整的语法形式的参数申明放在花括号内，并且有【可选的类型参数】，函数体跟在一个
        // 【“->”】之后。如果推断出改lambda的返回值类型不是Unit，那么该lambda主体的最后一个表达式会视为【返回值】
        //把所有的标注都留下，看起来如此：
        val sum = { x: Int, y: Int -> x + y }
        // 传递末尾的lambda表达式：
        //如果函数的最后一个参数是函数，那么作为相应参数传入的lambda表达式可以放在圆括号之外，
//        val product = items.fold(1) { acc, e -> acc * e } //{ acc, e -> acc * e } 放在圆括号外了
        // 并且如果这个lambda表达式是唯一的参数，圆括号可以省略：
        run { println("...") } //省略了圆括号

        //lambda表达式只有一个参数，如果编译器可以识别出签名，也可以不用申明唯一的参数并忽略“->”该参数
//        会隐式申明为it

        fun(x: Int, y: Int) {//匿名函数，没有函数名，函数体可以是一个代码块或者一个表达式
            x + y
        }

        val arraylist = ArrayList<String>(listOf("234"))
        //kotlin 中的ArrayList在代码中查看是对java的ArrayList起了一个别名
        //集合可以通过toList、toMutableList等方法浅复制一个副本，对原集合原色的修改都会反映在所有副本操作中。
        //通过复制得到的有相同原色的新集合如果原来的集合中添加或者删除元素，则不会影响副本，副本也可以独立进行更改。
        // 也就是：复制的集合对其原来的集合修改会【影响副本】，但是对于集合的【添加和删除】不会影响副本。

        //mutableListOf集合创建的集合，通过赋值的方式添加的引用，限制了其可变性，不能再add
        //也就是赋值的引用不能操作原来集合

        //区间的定义和转换
        val numbers = (1..100)
        val listNumbers = numbers.toList()

        listNumbers.sum()
        listNumbers.average()
        listNumbers.max()
        listNumbers.min()
//        listNumbers.sumBy {  }

        var listSring = mutableListOf("12312", "234234");
        arraylist.add("")
        listSring.add("")
        listSring.max()
        listSring.min()


        val mynumbers = mutableListOf("one", "two", "three", "four")
        mynumbers.add("five")   // 这是可以的


    }


    //有关协诚的使用

     suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了一些有用的事
        return 13
    }

    fun testCoroutines() {
        val job = GlobalScope.launch {
            try {
                val time = measureTimeMillis {
                    val one = async(start = CoroutineStart.LAZY) {
                       doSomethingUsefulOne()
                    }
                    one.start()
                }
                var number:Int = 0
                repeat(100_000_0) {
                    number+=1
                    println("I'm waiting for 1000 times--$number")
                    delay(1000)
                }
            } finally {
                withTimeout(1000) {
                    println("withTimeour")
                }
            }
        }
    }

    //定义协成类的 扩展方法
    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) send(x++) // 在流中开始从 1 生产无穷多个整数
    }

}