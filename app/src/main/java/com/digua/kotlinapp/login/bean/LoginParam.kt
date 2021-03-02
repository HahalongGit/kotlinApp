package com.digua.kotlinapp.login.bean

/**
 * 登录参数
 *
 * @author RunningDigua
 * @date 2021/3/2
 */
 class LoginParam constructor(){//主构造函数

    //次构造函数
    constructor(userName: String?, password: String?, userId: String?, token: String?) : this() {
        this.userName = userName
        this.password = password
        this.userId = userId
        this.token = token
    }

    init {
        //初始化块
    }
    var userName: String? = null
    var password: String? = null
    var userId: String? = null
    var token: String? = null
}