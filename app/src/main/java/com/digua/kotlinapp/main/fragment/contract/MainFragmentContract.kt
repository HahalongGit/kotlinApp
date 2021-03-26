package com.digua.kotlinapp.main.fragment.contract

import com.digua.kotlinapp.base.BaseView
import com.digua.kotlinapp.base.IPresenter
import com.digua.kotlinapp.main.bean.GoodsInfo

/**
 * MainFragmentContract
 *
 * @author RunningDigua
 * @date 2021/3/26
 */
interface MainFragmentContract{

    interface MainFragmentPresenter:IPresenter<MainFragmentView>{

        fun getGoodsInfoList(userId:String,pageIndex:Int)

    }

    interface MainFragmentView :BaseView{

        fun setGoodsData(list: MutableList<GoodsInfo>)

    }

}