package com.digua.componentbase;

import com.digua.componentbase.empty_service.EmptyAccountService;
import com.digua.componentbase.empty_service.EmptyWalletInfoService;
import com.digua.componentbase.service.IAccountService;
import com.digua.componentbase.service.IWalletInfoService;

/**
 * 提供服务的Factory
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class ServiceFactory {

    private ServiceFactory() {
    }

    /**
     * 用户登录信息
     */
    private IAccountService mAccountService;

    /**
     * 钱包服务新
     */
    private IWalletInfoService mWalletInfoService;


    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }

    /**
     * 获取用户登录信息
     * @return
     */
    public IAccountService getAccountService() {
        if (mAccountService == null) {// 防止报空异常
            mAccountService = new EmptyAccountService();
        }
        return mAccountService;
    }

    /**
     * 登录后设置登录信息Service
     * @param accountService
     */
    public void setAccountService(IAccountService accountService) {
        this.mAccountService = accountService;
    }


    /**
     * 其他需要电子钱包信息的模块 获取电子钱包信息
     *
     * @return
     */
    public IWalletInfoService getWalletInfoService() {
        if (mWalletInfoService == null) {
            mWalletInfoService = new EmptyWalletInfoService();
        }
        return mWalletInfoService;
    }

    /**
     * 钱包模块设置钱包信息
     *
     * @param walletInfoService
     */
    public void setEWalletInfoService(IWalletInfoService walletInfoService) {
        mWalletInfoService = walletInfoService;
    }

}
