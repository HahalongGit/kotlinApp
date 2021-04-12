package com.digua.componentbase.service;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

    /**
     * 添加一个Fragment
     * @param activity
     * @param containerid
     * @param fragmentManager
     * @param bundle
     * @return
     */
    Fragment newFragment(Activity activity, int containerid, FragmentManager fragmentManager, Bundle bundle,String tag);

}
