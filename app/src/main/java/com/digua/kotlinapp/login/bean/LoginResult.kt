package com.digua.kotlinapp.login.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * 登录返回结果
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
  class LoginResult() : Parcelable {

    private var token:String?=null;

    private var userId:String?=null;

    private var userName:String? = null;

    private var telphone:String?=null;


    constructor(parcel: Parcel) : this() {
        token = parcel.readString()
        userId = parcel.readString()
        userName = parcel.readString()
        telphone = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeString(userId)
        parcel.writeString(userName)
        parcel.writeString(telphone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResult> {
        override fun createFromParcel(parcel: Parcel): LoginResult {
            return LoginResult(parcel)
        }

        override fun newArray(size: Int): Array<LoginResult?> {
            return arrayOfNulls(size)
        }
    }

}