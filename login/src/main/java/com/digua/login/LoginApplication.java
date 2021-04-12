package com.digua.login;

import android.app.Application;

import com.digua.base.BaseApplication;
import com.digua.componentbase.ServiceFactory;
import com.digua.login.service.AccountService;

/**
 *
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class LoginApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void initModuleApp(Application application) {
        //在application中注册登录Service的实现  因为Application的onCreate 方法在library不会被调用
        ServiceFactory.getInstance().setAccountService(new AccountService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
