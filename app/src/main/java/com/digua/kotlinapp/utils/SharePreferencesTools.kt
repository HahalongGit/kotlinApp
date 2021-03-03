package com.digua.kotlinapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * SharePreferencesTools
 *
 * @author RunningDigua
 * @date 2021/3/3
 */
class SharePreferencesTools constructor(context: Context) {

    private val NAME = "KotlinAppFile"

    private val USER_ID = "userId"

    private var mSharedPreferences: SharedPreferences? = null;

    init {
        if (mSharedPreferences == null) {
            mSharedPreferences = context?.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }
    }

    fun putUserId(userId: String) {
        //Kotlin 中KTX 支持库 简化代码调用
        mSharedPreferences?.edit {
            putString(USER_ID, userId)
        }
    }

//    fun putUserId(userId: String): Boolean? {
//        val edit = mSharedPreferences?.edit()
//        return edit?.putString(USER_ID, userId)
//            ?.commit()
//    }

    fun getUserId(): String? {
        return mSharedPreferences?.getString(USER_ID, "")
    }

}