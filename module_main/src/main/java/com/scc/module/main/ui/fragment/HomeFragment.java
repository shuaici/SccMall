package com.scc.module.main.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scc.lib.componentbase.service.IAccountService;
import com.scc.lib.ui.BaseFragment;
import com.scc.lib.utils.MLog;
import com.scc.module.main.adapter.HomeFragmentAdapter;
import com.scc.module.main.bean.HomeFriendBean;
import com.scc.module.main.databinding.FragmentHomeBinding;
import com.scc.module.main.moudel.HomeFragmentViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    HomeFragmentViewModel homeViewModel;
    private List<HomeFriendBean> friendBeans = new ArrayList<>();
    private HomeFragmentAdapter adapter;
    @Autowired
    IAccountService accountService;
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ARouter.getInstance().inject(this);
        homeViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        homeViewModel.getHomeFriends().observe(getViewLifecycleOwner(), new Observer<List<HomeFriendBean>>() {
            @Override
            public void onChanged(List<HomeFriendBean> homeFriendBeans) {
                friendBeans.addAll(homeFriendBeans);
                adapter.notifyDataSetChanged();
            }
        });
        init();
    }

    private void init() {
        binding.frgmentHomeRvFriend.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeFragmentAdapter(getContext(), friendBeans);
        binding.frgmentHomeRvFriend.setAdapter(adapter);
        binding.frgmentHomeFab.setOnClickListener(v -> {
            //获取登录信息
            MLog.e("Login:"+accountService.isLogin());
            MLog.e("AccountId:"+accountService.getAccountId());
            //跳转登录页
//            ARouter.getInstance().build("/login/LoginActivity").navigation();
            //跳转收藏页
//            ARouter.getInstance().build("/collect/CollectListActivity").navigation();

        });
    }
}