package com.digua.base;

/**
 * 用来初始化模块Application的配置路径
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class AppConfig {

    /**
     * 登陆模块Application全路径
     */
    private static String LoginApp = "com.digua.login.LoginApplication";

    /**
     * 钱包 模块Application
     */
    private static String WalletApp = "com.digua.wallet.WalletApplication";

    public static String[] moduleApps = {
            LoginApp,
            WalletApp

    };

}
