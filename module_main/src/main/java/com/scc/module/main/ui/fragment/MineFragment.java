package com.scc.module.main.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.scc.lib.ui.BaseFragment;
import com.scc.module.main.adapter.MineFragmentAdapter;
import com.scc.module.main.bean.MineMyBean;
import com.scc.module.main.databinding.FragmentMineBinding;
import com.scc.module.main.moudel.MineViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MineFragment extends BaseFragment<FragmentMineBinding> {
    private MineViewModel mineViewModel;
    private MineFragmentAdapter adapter;
    private List<MineMyBean> myBeanList;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mineViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mineViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<MineMyBean>>() {
            @Override
            public void onChanged(List<MineMyBean> mineMyBeans) {
                myBeanList.addAll(mineMyBeans);
                adapter.notifyDataSetChanged();
            }
        });
        init();
    }

    private void init(){
        binding.fragmentMineRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        myBeanList = new ArrayList<>();
        adapter = new MineFragmentAdapter(getContext(),myBeanList);
        binding.fragmentMineRv.setAdapter(adapter);
    }
}