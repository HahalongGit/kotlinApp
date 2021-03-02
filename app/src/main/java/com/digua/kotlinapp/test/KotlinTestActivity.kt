package com.digua.kotlinapp.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digua.kotlinapp.databinding.ActivityKotlinTestBinding

class KotlinTestActivity : AppCompatActivity() {

//    private var mTvNameText = TODO("not yet init");

    private lateinit var binding: ActivityKotlinTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var innerClass = new  InnerClass();
    }

    //内部类
    // 内部类如何使用
     class InnerClass{



        fun showLoading() {

        }
    }

    fun test() {
//        var ss:String = "abc"
//        ss = null;
        // 直接定义了一个String赋值，这种变量不能为空，后续赋值null编译出错
        var name: String? = "abc"
        name = null
//        var length = name?.length ;
//      1.可为空的类型定义
//      对于name变量是可以为空的，在调用的使用使用“?.”的方式判断是否为空,相当于以下代码:
//              if(name!=null){
//                  var length = name.length
//              }
        //变量名称后边跟上？表示这个变了可以为空，如果调用可能出现空指针错误
//        var login: LoginParam = LoginParam()
//        login = null
//        login.hashCode()


        //2.变量的安全调用
//        安全调用操作符“?.”
        val a = "kotlin"
        val b:String?=null
        println(b?.length) //使用安全调用方式“?.”
        println(a?.length)//由于a定义的变量非空，因此不需要使用安全调用
        // 安全调用也可以出现在赋值表达式的左侧，如果安全调用的一部分是null，则不会完成赋值操作
        //person?.department?.head = manangerPoll.getManager //如果左侧一部分是空则不会执行右侧的赋值

        //3.Elvis操作符（?:）
        //当我们有一个可空的引用 b时，我们可以说b非空，否则使用某个非空的值
        var l:Int = if(b!=null)b.length else -1;
        //除了使用if else ，可以使用Elvis操作符表达写作：“?:”
        val ll = b?.length?:-1 //b采用安全调用符“?.”同时使用了Elvis表达式“?:”判断b是否空
        //因为throw和return在Kotlin中都是表达式，所以它们也可以用在Elvis操作符的右侧，这很方便如foo()：

        //4. “!!”操作符
//        “!!”是为NPE爱好者准备的：非空断言运算符，将任何值转换为非空类型，若该值为空则抛出空指针异常
        //我们可以写b!!,折回返回一个非空的b值，例如上面的例子中String类型的b为空，就会抛出NPE异常
//        val len = b?.length; //安全调用的写法
        val length = b!!.length  //如果b是空则抛出异常

        //5.类型的安全转换
//        一般如果对象不是目标类型则会报ClassCastException，另外的办法是使用安全带类型转换，
//        如果尝试不成功则返回null
        val aInt:Int? = a as?Int //对aInt 进行赋值，如果a是Int类型则赋值，否则返回null
        //如果有一个可以为空类型的集合，并且想要过滤非空元素，可以使用filterNitNull来实现
        val nullableList:List<Int?> = listOf(1,2,null,4)
        val intList:List<Int> = nullableList.filterNotNull()
    }

    fun foo(node:Node): String? {
        val parent = node.getParent()?:return null ;//“?:”表示如果node.getParent()是空则返回null
        val name = node.name?:throw IllegalArgumentException("name expected") //如果node.name是空则抛出异常
        return null
    }

    class Node{
        val name:String?=null;
        fun getParent():Node?{//方法返回值可能为空
            return Node()
        }
    }

}