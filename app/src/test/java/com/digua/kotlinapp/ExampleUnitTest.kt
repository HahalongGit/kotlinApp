package com.digua.kotlinapp

import com.digua.kotlinapp.login.bean.LoginParam
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import org.junit.Test

import org.junit.Assert.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.math.log
import kotlin.reflect.KProperty
import kotlin.system.measureTimeMillis

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
        //1.
        runBlocking {
            // 当我们使用 GlobalScope.launch 时，我们会创建一个顶层协程,虽然很轻量但还是会消耗资源，如果忘记对
            // 保持对新协程的引用，它还会继续执行。
            val job  = GlobalScope.launch {
                println("start------------")
                for ( i in 1..3){
                    delay(330)
                    println("i = $i --> "+ System.currentTimeMillis())
                }
                println(TAG+"--thread-1--"+Thread.currentThread().name)
                println("world ")
                test()
            }
            job.join() //// 等待直到子协程执行结束,不用设置Thread.sleep(3000)
            println("hello")
//            Thread.sleep(3000)  //// 阻塞主线程 3 秒钟来保证 JVM 存活 是什么意思？ 注释掉就launch就不执行了
            println(TAG+"--thread-2--"+Thread.currentThread().name);
        }

        //2.简化后的代码
        //协程作用域
        runBlocking {//外部协程（runBlocking）直到其作用于中启动的所有协程都执行完毕才会结束。
            launch {
                println("runBlocking-start------------")
                delay(2000)
                println(TAG+"runBlocking--thread-1--"+Thread.currentThread().name)
                println("runBlocking-world ")
            }
            println("runBlocking-hello")
            println(TAG+"runBlocking--thread-2--"+Thread.currentThread().name);
        }


    }

    private suspend fun test(){
        println(TAG+"thread-test-1"+Thread.currentThread().name);
        withContext(Dispatchers.IO){
            println(TAG+"thread-test-2"+Thread.currentThread().name);
        }
    }

    @Test
    fun main() = runBlocking {

        launch {
            doWorld()
        }
//        CoroutineContext(coroutineContext) {
//            launch {
//                delay(500L)
//                println("task from nested launch")
//            }
//            delay(100L)
//            println("task from corountine scope")
//        }

        println("Coroutine scope is over")

    }

    @Test
    fun main1() = runBlocking {
        repeat(100_000){//循环10万次
            launch {
                print(".")
                // 协程能够循环创建若干，不会造成内存泄漏
            }
        }
    }

    @Test
    fun main2() = runBlocking {

        val job = launch {
            repeat(10){
                i -> println("job: I'm sleep $i")
                delay(500L)
            }
        }
        delay(1300L)  //1300之后开始执行
        println("main: I'm tried of waiting")
        job.cancel() //取消协程执行，否则 会一直执行完repeat的次数
        job.join()
        println("main : Now I can quit")
    }

    @Test
    fun main3() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i<6) { // 一个执行计算的循环，只是为了占用 CPU
//                把while 替换为isActive 这是一个扩展属性 ，则这个计算的消息循环可以取消，可取消的计算协程
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L //每次间隔500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束，调用了方法后依然执行，直到循环结束
        println("main: Now I can quit.")
    }

    @Test
    fun main4() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {//添加了funally delay1300执行完毕了 自动停止，
                println("job: I'm running finally")
            }
        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并且等待它结束 //添加了funally delay1300执行完毕了 自动停止，
        println("main: Now I can quit.")
    }

    @Test
    fun main5() = runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L) //请求超时
            }
        }
    }

    @Test
    fun main6() = runBlocking {
//        使用 withTimeoutOrNull不会抛出异常
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // 在它运行得到结果之前取消它
        }
        println("Result is $result")
    }

    private suspend fun doWorld() {
        delay(200L)
        println("task form runblocking")
    }


    @Test
    fun main7() = runBlocking<Unit> {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }//使用async显式并发执行
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
            one.start()
            two.start()
            //添加CoroutineStart.LAZY 后变为惰性的，要么手动调用其start()方法开始执行，或者在调用await的时候开始执行
            //调用await开始执行的方式导致不同的方法又变成了串行执行，时间加长了
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")  //并发执行时间会缩短为一半
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了些有用的事
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }

    fun testFill(){
        val array:Array<CharSequence> = arrayOf("31","","aa")
        fill(array,"32")
    }

    private fun fill(dest: Array<in String>, value: String) {
        dest[0] = value
    }

    // 创建接口
    interface Base {
        fun print()
    }

    // 实现此接口的被委托的类
    class BaseImpl(val x: Int) : Base {
        override fun print() {
            print(x)
        }
    }

    // 通过关键字 by 建立委托类
    class Derived(base: Base) : Base by base{
        //Derived虽然实现了Base接口，但是自己不执行，委托给传入的参数的类去实行（让代理执行）
        //传入了一个BaseImpl

        //如果这个Derived自己实现了Base的方法委托就不会生效！！！

//        override fun print() {
////            println("derived:")
//        }
    }

    @Test
    fun main8() {
        val b = BaseImpl(10)
        Derived(b).print() // 输出 10
    }

    class Example {
        var p: String by Delegate()
    }
    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            // thisRef 值得是包含委托属性的类对象，参数二表示属性本身
            return "$thisRef, 这里委托了 ${property.name} 属性"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            // 对于setValue方法，参数三是 要set 的值
            println("$thisRef 的 ${property.name} 属性赋值为 $value")
        }
    }

    @Test
    fun test9(){
        val e = Example()
        println(e)
        println("-----------------------------------------")
        println(e.p)     // 访问该属性，调用 getValue() 函数

        e.p = "Runoob"   // 调用 setValue() 函数
        println(e.p)
    }



}
