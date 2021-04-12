package com.digua.componentbase.service;

/**
 * 提供登录信息的接口
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public interface IAccountService {

    boolean isLogin();

    /**
     * 获取账户id
     * @return
     */
    String getAccountId();

    /**
     * 获取用户名
     * @return
     */
    String getUserName();


}
