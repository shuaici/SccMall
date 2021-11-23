package com.scc.lib.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>{
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private OnLongClickListener onLongClickListener;
    private OnItemClickListener onItemClickListener;
    public BaseRecyclerViewAdapter(Context mContext, List<T> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(mContext,mInflater.inflate(getItemLayoutId(viewType),parent,false));
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView,viewHolder.getLayoutPosition());
                }
            });
        }
        if (onLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClickListener.onLongClick(viewHolder.itemView,viewHolder.getLayoutPosition());
                    //消费掉
                    return true;
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        convert(holder,position,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public void addData(int pos,T itemBean){
        if (mDatas.size()-1>pos) {
            mDatas.add(pos,itemBean);
            notifyItemInserted(pos);
        }
    }
    abstract public int getItemLayoutId(int viewType);
    abstract public void convert(RecyclerViewHolder holder, int position, T bean);


    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView,int pos);
    }
    public interface OnLongClickListener{
        void onLongClick(View itmView,int pos);
    }
}
