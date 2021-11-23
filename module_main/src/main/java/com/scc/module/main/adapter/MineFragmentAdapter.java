package com.scc.module.main.adapter;

import android.content.Context;
import android.view.View;


import com.alibaba.android.arouter.launcher.ARouter;
import com.scc.lib.ui.BaseRecyclerViewAdapter;
import com.scc.lib.ui.RecyclerViewHolder;
import com.scc.module.main.R;
import com.scc.module.main.bean.MineMyBean;

import java.util.List;

public class MineFragmentAdapter extends BaseRecyclerViewAdapter<MineMyBean> {

    public MineFragmentAdapter(Context mContext, List<MineMyBean> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_fragment_mine;
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, MineMyBean bean) {
        holder.setText(R.id.item_fragment_mine_tv_name, bean.getName());
        holder.setOnClickListener(R.id.item_fargment_mine_btn_look, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/webview/WebViewActivity")
                        .withString("url", bean.getUrl())
                        .withString("content",bean.getName())
                        .navigation();
            }
        });
    }

}
