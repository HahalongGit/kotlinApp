package com.digua.componentbase.empty_service;

import com.digua.componentbase.service.IWalletInfoService;

/**
 * 获取电子钱数据包空实现
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class EmptyWalletInfoService implements IWalletInfoService {

    @Override
    public boolean isOpenEWallet() {
        return false;
    }

    @Override
    public String getEWalletId(String userId) {
        return null;
    }

    @Override
    public double getEWalletBalance(String userId) {
        return 0;
    }
}
