package com.digua.kotlinapp.main

import android.view.View
import android.widget.Toast
import com.digua.kotlinapp.R
import com.digua.kotlinapp.basemvp.BaseMvpActivity
import com.digua.kotlinapp.databinding.ActivityMainBinding
import com.digua.kotlinapp.getLongestString
import com.digua.kotlinapp.main.bean.ResultBean
import com.digua.kotlinapp.main.presenter.MainPresenter
import com.digua.kotlinapp.main.presenter.contract.MainContract

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.MainView,
    View.OnClickListener {//传递一个MainPresenter 在Base中反射创建对象，同时传递一个页面view给Presenter

    private lateinit var mBinding: ActivityMainBinding

    companion object {
        val TAG: String = MainActivity.javaClass.name + "......"
    }

    override fun getLayoutView(): View {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun initView() {
        mBinding.btnTestSuspend.setOnClickListener(this)
        mBinding.btnOperation.setOnClickListener(this)
        mBinding.tvContextText.text = "这是Main页面"

        mBinding.btnOperation.setOnClickListener {
            when(it.id){
                R.id.btnTestSuspend -> {
                    Toast.makeText(this,"点击了..测试suspend关键字",Toast.LENGTH_SHORT).show()
                }
            }
            //简化的写法
        }
    }


    override fun initData() {
        val list = listOf("12", "lili", "xinyu")
        val longest = list.getLongestString()
        println("longest:$longest")

        mPresenter?.queryData("传递的参数...")
        mPresenter?.queryDataWithKotlin1()
        mPresenter?.queryDataWithRxjava("参数2")
        mPresenter?.queryDataWithKotlin("参数3")

        //函数式请求的方式调用
        mPresenter?.queryDataWithKotlinFunctionParam("普通参数一个"){
            println("queryDataWithKotlinFunctionParam-size--:" + it.size)
            println("queryDataWithKotlinFunctionParam-threadName--:" +Thread.currentThread().name)
            Toast.makeText(this,"size:"+it.size,Toast.LENGTH_SHORT).show()
        }


    }

    override fun setResult(result: ResultBean) {
        println(TAG + "setResult-currentThread--" + Thread.currentThread().name)
        println("result-size--:" + result.size)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTestSuspend -> {
                Toast.makeText(this, "测试testSuspend", Toast.LENGTH_SHORT).show()
                mPresenter?.testSuspend()
            }
            R.id.btnOperation -> {// btnTestSuspend点击后主线程被占用，这里出现无响应ANR
                Toast.makeText(this, "正在测试testSuspend了", Toast.LENGTH_SHORT).show()
            }
        }

        if (v?.id == R.id.btnTestSuspend) {

        }
    }
}
