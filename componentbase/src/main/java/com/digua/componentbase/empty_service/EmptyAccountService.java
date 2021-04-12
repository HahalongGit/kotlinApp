package com.digua.componentbase.empty_service;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

    @Override
    public Fragment newFragment(Activity activity, int containerid,
                                FragmentManager fragmentManager, Bundle bundle,String tag) {
        return null;
    }
}
