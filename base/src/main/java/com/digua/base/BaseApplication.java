package com.digua.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 提供一个BaseApplication来初始化 服务和其他数据
 * 因为Application的onCreate在library的时候不会被调用
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    public abstract void initModuleApp(Application application);

    public abstract void initModuleData(Application application);

}
