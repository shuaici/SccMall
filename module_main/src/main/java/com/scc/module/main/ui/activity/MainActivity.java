package com.scc.module.main.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.scc.lib.ui.BaseActivity;
import com.scc.lib.utils.MLog;
import com.scc.module.main.R;
import com.scc.module.main.adapter.MainViewPager2Adapter;
import com.scc.module.main.databinding.ActivityMainBinding;
import com.scc.module.main.moudel.MainViewModel;
import com.scc.module.main.ui.fragment.AddressBookFragment;
import com.scc.module.main.ui.fragment.HomeFragment;
import com.scc.module.main.ui.fragment.MineFragment;
import com.scc.module.main.ui.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.e("MainActivity");
    }
    @Override
    protected void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new AddressBookFragment());
        list.add(new SearchFragment());
        list.add(new MineFragment());
        binding.mainVp2.setAdapter(new MainViewPager2Adapter(this, list));
    }

    @Override
    protected void initView() {
        //item处于选中状态，再次点击时该Item触发事件。
        binding.mainBnv.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(MenuItem item) {
                MLog.e("Reselected" + item.toString());
            }
        });
        binding.mainBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                MLog.e("Selected" + item.toString() + item.getItemId());
                //这里设置为：当点击到某子项，ViewPager就滑动到对应位置
                int itemId = item.getItemId();
                if (itemId == R.id.main_bnv_item_home) {
                    binding.mainVp2.setCurrentItem(0);
                    return true;
                } else if (itemId == R.id.main_bnv_item_addressbook) {
                    binding.mainVp2.setCurrentItem(1);
                    return true;
                } else if (itemId == R.id.main_bnv_item_search) {
                    binding.mainVp2.setCurrentItem(2);
                    return true;
                } else if (itemId == R.id.main_bnv_item_mine) {
                    binding.mainVp2.setCurrentItem(3);
                    return true;
                }
                return false;
            }
        });
        //禁止滑动
        binding.mainVp2.setUserInputEnabled(false);
//        binding.mainVp2.getOrientation()
//        binding.mainVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.mainVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //注意这个方法滑动时会调用多次，下面是参数解释：
                //position当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                //positionOffset:表示从位置的页面偏移的[0,1]的值。
                //positionOffsetPixels:以像素为单位的值，表示与位置的偏移
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                //该方法只在滑动停止时调用，position滑动停止所在页面位置

                //当滑动到某一位置，导航栏对应位置被按下
                binding.mainBnv.getMenu().getItem(position).setChecked(true);
                //这里使用navigation.setSelectedItemId(position);无效，
                //setSelectedItemId(position)的官网原句：Set the selected
                // menu item ID. This behaves the same as tapping on an item
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //state表示滑动状态，有三个值：
                //0：空闲、稳定状态，实际值为0
                //1：正在被用户拖动;
                //2：正在稳定到最终位置(拖动结束);
                super.onPageScrollStateChanged(state);
            }
        });
    }

}