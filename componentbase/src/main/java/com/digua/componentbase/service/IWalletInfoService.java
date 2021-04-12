package com.digua.componentbase.service;

/**
 * 钱包信息服务
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public interface IWalletInfoService {


    /**
     * 是否开通电子钱包
     * @return
     */
    boolean isOpenEWallet();

    /**
     * 获取钱包id
     * @param userId
     * @return
     */
    String getEWalletId(String userId);

    /**
     * 获取钱包余额
     * @param userId
     * @return
     */
    double getEWalletBalance(String userId);

}
