package com.digua.wallet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.digua.componentbase.ServiceFactory;
import com.digua.wallet.bean.WalletInfo;
import com.digua.wallet.databinding.WalletActivityMainBinding;
import com.digua.wallet.utils.WalletUtils;

@Route(path = "/wallet/main")
public class MainActivity extends AppCompatActivity {

    private WalletActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = WalletActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        //钱包主页面 模拟钱包中获取到了钱包信息 保存下钱包信息
        WalletInfo walletInfo = new WalletInfo();
        walletInfo.setBalance(3432.43);
        walletInfo.setWalletId("41s32v37eexed23253d3s8je");
        WalletUtils.walletInfo = walletInfo;
        if (ServiceFactory.getInstance().getAccountService().isLogin()) {
            mBinding.tvWalletMain.setText("我的钱包--钱包余额：" + WalletUtils.walletInfo.getBalance());
        } else {
            mBinding.tvWalletMain.setText("我的钱包--用户未登录");
        }
    }
}
