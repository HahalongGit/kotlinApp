package com.digua.login.service;

import com.digua.componentbase.service.IAccountService;
import com.digua.login.utils.AccountUtils;

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class AccountService implements IAccountService {

    @Override
    public boolean isLogin() {
        return AccountUtils.userInfo != null;
    }

    @Override
    public String getAccountId() {
        return AccountUtils.userInfo == null ? null : AccountUtils.userInfo.getAccountId();
    }

    @Override
    public String getUserName() {
        return AccountUtils.userInfo == null?"":AccountUtils.userInfo.getUserName();
    }

}
