package com.digua.kotlinapp.main.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.digua.kotlinapp.TestActivity
import com.digua.kotlinapp.base.BaseMvpFragment
import com.digua.kotlinapp.databinding.MainFragmentMainLayoutBinding
import com.digua.kotlinapp.main.bean.GoodsInfo
import com.digua.kotlinapp.main.fragment.contract.MainFragmentContract
import com.digua.kotlinapp.utils.LoginUtil

/**
 * MainFragment
 *
 * @author RunningDigua
 * @date 2021/3/26
 */
class MainFragment : BaseMvpFragment<MianFragmentPresenter>(),
    MainFragmentContract.MainFragmentView {

    private lateinit var mBinding: MainFragmentMainLayoutBinding

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        LoginUtil.e(TAG, "onAttach");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginUtil.e(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LoginUtil.e(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoginUtil.e(TAG, "onViewCreated")
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

    override fun onStop() {
        super.onStop()
        LoginUtil.e(TAG, "onStop")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        LoginUtil.e(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginUtil.e(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LoginUtil.e(TAG, "onDetach")
    }

    override fun getLayoutView(inflater: LayoutInflater): View {
        mBinding = MainFragmentMainLayoutBinding.inflate(inflater)
        return mBinding.root
    }


    override fun initView() {
        mBinding.btnTestActivty.setOnClickListener {
            val intent = Intent(mContext, TestActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initData() {
        mPresenter.getGoodsInfoList("", 1)
    }

    companion object {
        const val TAG = "MainFragment.java--"
    }

    override fun setGoodsData(list: MutableList<GoodsInfo>) {
        Toast.makeText(mContext, "set数据..", Toast.LENGTH_SHORT).show()
    }


}