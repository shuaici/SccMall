package com.scc.lib.ui;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
  * 创建人：帅次
  * 创建时间：2021/10/27
  * 功能：上拉加载更多
  */
public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private int countItem;
    private int lastItem;
    private boolean isScrolled = false;
    private static boolean isAllScreen = false;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * 加载接口
     * @param countItem 总数量
     * @param lastItem  最后显示的position
     */
    protected abstract void onLoading(int countItem, int lastItem);

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState==RecyclerView.SCROLL_STATE_IDLE){
            Log.d("test","SCROLL_STATE_IDLE,空闲");
        }
        else if (newState==RecyclerView.SCROLL_STATE_DRAGGING){
            Log.d("test","SCROLL_STATE_DRAGGING,拖拽");
        }
        else if (newState==RecyclerView.SCROLL_STATE_SETTLING){
            Log.d("test","SCROLL_STATE_SETTLING,固定");
        }
        else{
            Log.d("test","其它");
        }
        //拖拽或者惯性滑动时isScolled设置为true
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
            isScrolled = true;
            isAllScreen = true;
        } else {
            isScrolled = false;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            mLayoutManager = recyclerView.getLayoutManager();
            countItem = mLayoutManager.getItemCount();
            lastItem = ((LinearLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (isScrolled && countItem != lastItem && lastItem == countItem - 1) {
            onLoading(countItem, lastItem);
        }
    }
    public static boolean isAllScreen(){
        return isAllScreen;
    }
}
