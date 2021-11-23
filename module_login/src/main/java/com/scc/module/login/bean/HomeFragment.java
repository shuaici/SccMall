//package com.scc.module.login.bean;
//
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.StaggeredGridLayoutManager;
//
//import android.util.Log;
//import android.view.View;
//
//import com.netease.huiying.R;
//import com.netease.huiying.adapter.HomeAdapter;
//import com.netease.huiying.bean.HomeBean;
//import com.netease.huiying.mvp.home.HomePresenter;
//import com.netease.huiying.mvp.home.HomeView;
//import com.netease.huiying.ui.BaseMvpFragment;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class HomeFragment extends BaseMvpFragment<HomeView, HomePresenter> implements HomeView {
//
//    private RecyclerView rcy_home;
//    private ArrayList<HomeBean.DataBean.ListBean.TargetBean.ReceiveUserBean> homeBeans;
//    private HomeAdapter homeAdapter;
//
//    @Override
//    public void successHome(HomeBean homeBean) {
//
//        List<HomeBean.DataBean.ListBean> listBeans = new ArrayList<>();
//        HomeBean.DataBean.ListBean list = listBeans.get(0);
//        list.getTarget();
//        List<HomeBean2.DataBean.ListBean> listBeans2 = new ArrayList<>();
//        HomeBean2.DataBean.ListBean bean = listBeans2.get(0);
//        bean.getTarget();
//        for (HomeBean2.DataBean.ListBean listBean : listBeans2) {
//            listBeans.add(listBeans2);
//        }
//        listBeans.addAll(listBeans2);
//        homeAdapter.notifyDataSetChanged();
//
//    }
//
//    @Override
//    protected HomePresenter createPresenter() {
//        return new HomePresenter();
//    }
//
//    @Override
//    public void error(String error) {
//
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        presenter.getHomeData();
//    }
//
//    @Override
//    protected void initView(View inflate) {
//
//        rcy_home = inflate.findViewById(R.id.rcy_home);
//
//        rcy_home.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        homeBeans = new ArrayList<>();
//
//        homeAdapter = new HomeAdapter(getContext(), homeBeans);
//
//        rcy_home.setAdapter(homeAdapter);
//
//    }
//
//    @Override
//    protected int getLayout() {
//        return R.layout.fragment_home;
//    }
//}