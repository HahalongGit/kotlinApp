package com.digua.componentbase.empty_service;

import com.digua.componentbase.service.IAccountService;

/**
 * 账户信息接口的空实现
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class EmptyAccountService implements IAccountService {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }
}
