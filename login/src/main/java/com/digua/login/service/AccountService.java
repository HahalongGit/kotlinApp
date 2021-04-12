package com.digua.login.service;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.digua.componentbase.service.IAccountService;
import com.digua.login.ui.login.fragment.UserFragment;
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
        return AccountUtils.userInfo == null ? "" : AccountUtils.userInfo.getUserName();
    }

    /**
     * 给需要Fragment的 module 提供一个Fragment 添加到提供的containerid上
     * 注意：对于Fragment也可以采用反射的方式创建，在base模块保存需要的Fragment的路径，在需要的模块中通过发射获取
     * 那种方式适合当前使用，就使用那种方式。
     *
     * @param activity
     * @param containerid
     * @param fragmentManager
     * @param bundle
     * @return
     */
    @Override
    public Fragment newFragment(Activity activity, int containerid, FragmentManager fragmentManager,
                                Bundle bundle, String tag) {
        UserFragment userFragment = new UserFragment();
        fragmentManager.beginTransaction()
                .add(containerid, userFragment, tag)
                .commit();
        return userFragment;
    }

}
