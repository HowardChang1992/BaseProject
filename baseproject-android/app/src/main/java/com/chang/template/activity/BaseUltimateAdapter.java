package com.chang.template.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Howard Chang on 2016/12/19
 */
public abstract class BaseUltimateAdapter<T> extends UltimateViewAdapter {

    protected Context context;
    protected List<T> dataList = new ArrayList<>();


    protected abstract void onBaseBindViewHolder(RecyclerView.ViewHolder holder, int position);
    protected abstract RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent);
    protected int getBaseAdapterItemCount() {
        return dataList.size();
    }

    public void add(T data) {
        dataList.add(data);
    }
    public void addAll(List<T> data) {
        dataList.addAll(data);
    }
    public void clear() {
        dataList.clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();
        return onBaseCreateViewHolder(parent);
    }

    @Override
    public int getAdapterItemCount() {
        return getBaseAdapterItemCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBaseBindViewHolder(holder, position);
    }

    @Override
    public long generateHeaderId(int position) {
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder newFooterHolder(View view) {
        return new UltimateRecyclerviewViewHolder<>(view);
    }

    @Override
    public RecyclerView.ViewHolder newHeaderHolder(View view) {
        return new UltimateRecyclerviewViewHolder<>(view);
    }

}
