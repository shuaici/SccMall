package com.scc.module.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


import com.scc.lib.ui.BaseRecyclerViewAdapter;
import com.scc.lib.ui.RecyclerViewHolder;
import com.scc.module.main.R;
import com.scc.module.main.bean.HomeFriendBean;

import java.util.List;

public class HomeFragmentAdapter extends BaseRecyclerViewAdapter<HomeFriendBean> {
    public HomeFragmentAdapter(Context mContext, List<HomeFriendBean> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_fragment_home;
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, HomeFriendBean bean) {
        holder.setText(R.id.item_fragment_home_tv_name,bean.getName());
        holder.setOnClickListener(R.id.item_fragment_home_tv_name, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,bean.getName()+"：你想干什么？",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
