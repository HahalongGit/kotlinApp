package com.digua.wallet;

import android.app.Application;

import com.digua.base.BaseApplication;
import com.digua.componentbase.ServiceFactory;
import com.digua.wallet.service.WalletInfoService;

/**
 * 钱包应用application
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class WalletApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //Wallet 单独编译运行时调用
        initModuleApp(this);
        initModuleData(this);
    }

    /**
     * Wallet module 作为library运行时调用
     * @param application
     */
    @Override
    public void initModuleApp(Application application) {
        //注册电子钱包信息Service
        ServiceFactory.getInstance().setEWalletInfoService(new WalletInfoService());
    }

    /**
     * 这里做一些Application的oncCreate的操作
     * Wallet module 作为library运行时调用
     * @param application
     */
    @Override
    public void initModuleData(Application application) {

    }
}
