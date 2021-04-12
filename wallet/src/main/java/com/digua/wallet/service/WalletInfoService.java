package com.digua.wallet.service;

import com.digua.componentbase.service.IWalletInfoService;
import com.digua.wallet.utils.WalletUtils;

/**
 * 电子钱包业务服务
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class WalletInfoService implements IWalletInfoService {

    @Override
    public boolean isOpenEWallet() {
        return WalletUtils.walletInfo != null;
    }

    @Override
    public String getEWalletId(String userId) {
        return WalletUtils.walletInfo == null ? null : WalletUtils.walletInfo.getWalletId();
    }

    @Override
    public double getEWalletBalance(String userId) {
        return WalletUtils.walletInfo == null ? null : WalletUtils.walletInfo.getBalance();
    }
}
