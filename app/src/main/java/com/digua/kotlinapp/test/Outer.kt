package com.digua.kotlinapp.test

/**
 *
 *
 * @author RunningDigua
 * @date 2021/3/3
 */
class Outer {


    fun setConClickListener(onClickListener: OnClickListener?){
        onClickListener?.onClick()
    }

    interface OnClickListener{
        fun onClick();
        fun onLongClick(position:Int)
    }


    class MyInnerClass{
        fun foo() = 2
    }

    /**
     * 内部类用inner关键字标识，
     */
    inner class Inner{
        fun getUserInfo():String{
            return "lili"
        }
    }

}