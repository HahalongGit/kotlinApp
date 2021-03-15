package com.digua.kotlinapp.main

import android.view.View
import com.digua.kotlinapp.base.BaseActivity
import com.digua.kotlinapp.databinding.ActivityMainBinding
import com.digua.kotlinapp.main.bean.ResultBean
import com.digua.kotlinapp.main.presenter.MainPresenter
import com.digua.kotlinapp.main.presenter.contract.MainContract

class MainActivity : BaseActivity<MainPresenter>(),MainContract.MainView {

    private lateinit var mBinding: ActivityMainBinding

    companion object{
        val TAG: String = MainActivity.javaClass.name+"......"
    }

    override fun getLayoutView(): View {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun initView() {
        mBinding.tvContextText.text = "这是Main页面"
    }

    override fun initPresenter() {
        mPresenter = MainPresenter(this)
        print(TAG+"initPresenter")
        mPresenter?.queryData("传递的参数...")
        mPresenter?.queryDataWithKotlin1()
        mPresenter?.queryDataWithRxjava("参数2")
        mPresenter?.queryDataWithKotlin("参数3")
    }

    override fun initData() {


    }

    override fun setResult(result: ResultBean) {
        println(TAG+"setResult")
    }
}
