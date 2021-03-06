package com.digua.kotlinapp.main.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.digua.kotlinapp.R
import com.digua.kotlinapp.TestActivity
import com.digua.kotlinapp.base.BaseMvpFragment
import com.digua.kotlinapp.databinding.AppMainFragmentMainLayoutBinding
import com.digua.kotlinapp.main.bean.GoodsInfo
import com.digua.kotlinapp.main.fragment.contract.MainFragmentContract
import com.digua.kotlinapp.utils.LoginUtil
import pub.devrel.easypermissions.EasyPermissions

/**
 * MainFragment
 *
 * @author RunningDigua
 * @date 2021/3/26
 */
class MainFragment : BaseMvpFragment<MianFragmentPresenter>(),
    MainFragmentContract.MainFragmentView,
    EasyPermissions.PermissionCallbacks,
    View.OnClickListener {

    private lateinit var mBinding: AppMainFragmentMainLayoutBinding

    private val PERMISSION_CODE = 10001

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LoginUtil.e(TAG, "onAttach");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginUtil.e(TAG, "onCreate--$savedInstanceState")
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
        mBinding = AppMainFragmentMainLayoutBinding.inflate(inflater)
        return mBinding.root
    }


    override fun initView() {
        mBinding.btnTestActivty.setOnClickListener(this)
        mBinding.btnModuleLogin.setOnClickListener(this)
        mBinding.btnModuleShare.setOnClickListener(this)
        mBinding.btnModuleWallet.setOnClickListener(this)
        mBinding.btnTest.setOnClickListener(this)
    }

    /**
     * 启动外部应用的Activty
     */
    private fun navigateToPartOne() {
        val intent = Intent()
        intent.setClassName(
            "com.lll.beizertest",
            "com.lll.beizertest.PartOneActivity"
        );
        startActivity(intent)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //将结果转发给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

        // 授权了
        if (requestCode == PERMISSION_CODE) {
            navigateToPartOne()
            Toast.makeText(mContext, "授权成功了了" + perms[0], Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

        // 未授权
        Toast.makeText(mContext, "onPermissionsDenied 未授权", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_testActivty -> {
//            val intent = Intent(mContext, TestActivity::class.java)
//            startActivity(intent)

                //自定义权限测试
                val PERMISSION_STORAGE_MSG = "请授予权限，否则影响部分使用功能"
                val perms = "com.lll.beizertest.permission.PART_ONE_ACCESS"
                if (EasyPermissions.hasPermissions(mContext, perms)) {
                    navigateToPartOne()
                } else {
                    EasyPermissions.requestPermissions(
                        this,
                        PERMISSION_STORAGE_MSG,
                        PERMISSION_CODE,
                        perms
                    )
                }

            }
            R.id.btn_moduleLogin -> {
                ARouter.getInstance()
                    .build("/account/login")
                    .navigation()
            }
            R.id.btn_moduleShare -> {//ARouter 的path（/share/main）不匹配时会提示path不匹配，不会报错
                ARouter.getInstance()
                    .build("/share/main")
                    .withString("title", "进入分享")
                    .navigation()
            }
            R.id.btn_moduleWallet -> {
                ARouter.getInstance()
                    .build("/wallet/main")
                    .navigation()
            }
            R.id.btn_test -> {
                Toast.makeText(mContext, "点击了按钮", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
