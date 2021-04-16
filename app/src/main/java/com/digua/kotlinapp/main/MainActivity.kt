package com.digua.kotlinapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.digua.componentbase.ServiceFactory
import com.digua.kotlinapp.R
import com.digua.kotlinapp.basemvp.BaseMvpActivity
import com.digua.kotlinapp.databinding.AppActivityMainBinding
import com.digua.kotlinapp.getLongestString
import com.digua.kotlinapp.main.bean.ResultBean
import com.digua.kotlinapp.main.fragment.MainFragment
import com.digua.kotlinapp.main.presenter.MainPresenter
import com.digua.kotlinapp.main.presenter.contract.MainContract
import com.digua.kotlinapp.other.ViewGroupDrawSequenceActivity
import com.digua.kotlinapp.utils.LoginUtil

@Route(path = "/main/mainActivity")
class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.MainView,
    View.OnClickListener {//传递一个MainPresenter 在Base中反射创建对象，同时传递一个页面view给Presenter

    private lateinit var mBinding: AppActivityMainBinding

    private val mFragmentManager = supportFragmentManager

    companion object {
        const val TAG: String = "MainActivity.java--"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginUtil.e(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        LoginUtil.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        LoginUtil.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        LoginUtil.e(TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        LoginUtil.e(TAG, "onRestart")
    }

    override fun onStop() {
        super.onStop()
        LoginUtil.e(TAG, "onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        LoginUtil.e(TAG, "onDestroy")
    }

    override fun getLayoutView(): View {
        mBinding = AppActivityMainBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun initView() {
        mBinding.btnTestSuspend.setOnClickListener(this)
        mBinding.btnDrawSequence.setOnClickListener(this)
        mBinding.btnReplaceFragment.setOnClickListener(this)
        mBinding.tvContextText.text = "这是Main页面"

    }


    override fun onResumeFragments·() {
        super.onResumeFragments()
        LoginUtil.e(TAG, "onResumeFragments")
        addFragment()
        //推荐在onResumeFragments 中执行Fragment的commit方法，避免出错
    }

    private fun addFragment() {
        val beginTransaction = mFragmentManager.beginTransaction()
        val mainFragment = MainFragment()
        beginTransaction.add(R.id.fl_container, mainFragment)
            .commit()
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
        mPresenter?.queryDataWithKotlinFunctionParam("普通参数一个") {
            println("queryDataWithKotlinFunctionParam-size--:" + it.size)
            println("queryDataWithKotlinFunctionParam-threadName--:" + Thread.currentThread().name)
            Toast.makeText(this, "size:" + it.size, Toast.LENGTH_SHORT).show()
        }


    }

    override fun setResult(result: ResultBean) {
        println(TAG + "setResult-currentThread--" + Thread.currentThread().name)
        println("result-size--:" + result.size)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTestSuspend -> {
                Toast.makeText(this, "点击了..测试suspend关键字", Toast.LENGTH_SHORT).show()
                mPresenter?.testSuspend()
            }
            R.id.btnDrawSequence -> {
                val intent = Intent(this, ViewGroupDrawSequenceActivity::class.java)
                startActivity(intent)
            }
            R.id.btnOperation -> {// btnTestSuspend点击后主线程被占用，这里出现无响应ANR
                Toast.makeText(this, "正在测试testSuspend了", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_replaceFragment -> {
                //代码隔离的情况下，添加一个其他模块的 Fragment 到本页面
                ServiceFactory.getInstance().accountService.newFragment(
                    this,
                    R.id.fl_container,
                    mFragmentManager,
                    null,
                    null
                )
            }
        }
    }
}
