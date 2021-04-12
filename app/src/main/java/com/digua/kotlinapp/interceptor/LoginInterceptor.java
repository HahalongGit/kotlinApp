package com.digua.kotlinapp.interceptor;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.digua.componentbase.ServiceFactory;
import com.digua.kotlinapp.utils.LoginUtil;

/**
 * 阿里巴巴 ARouter路由拦截
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
@Interceptor(priority = 8, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor {

    private Context mContext;

    private static final String TAG = "LoginInterceptor";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getPath().equals("/share/main")) {
            if (ServiceFactory.getInstance().getAccountService().isLogin()) {
                callback.onContinue(postcard);
            } else {
                //拦截器运行在ARouter线程池
                LoginUtil.INSTANCE.e(TAG, "currentThread---" + Thread.currentThread().getName());
//                callback.onInterrupt(new RuntimeException("用户未登录，请先登录！"));
                callback.onContinue(postcard);
            }
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        this.mContext = context;
    }
}
