package com.digua.login.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.digua.login.databinding.LoginFragmentUserLayoutBinding;

/**
 * 如何调用不同组件直接的Fragment
 *
 * @author RunningDigua
 * @date 2021/4/12
 */
public class UserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LoginFragmentUserLayoutBinding binding = LoginFragmentUserLayoutBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}
