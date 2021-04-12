package com.digua.share;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.digua.componentbase.ServiceFactory;
import com.digua.share.databinding.ShareActivityMainBinding;

/**
 * 分享主页面
 */
@Route(path = "/share/main")
public class MainActivity extends AppCompatActivity {

    private ShareActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ShareActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    private void share() {
        if (ServiceFactory.getInstance().getAccountService().isLogin()) {
            Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "用户未登陆~，请先登陆！", Toast.LENGTH_SHORT).show();
        }
    }
}
