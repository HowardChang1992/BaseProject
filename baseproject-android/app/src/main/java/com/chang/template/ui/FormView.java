package com.chang.template.ui;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Howard Chang on 2016/11/2
 */
public class FormView extends LinearLayout {
    private FormView.OnItemClickListener onItemClickListener;
    private FormView.Adapter adapter;

    private DataSetObserver dataSetObserver = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            FormView.this.reloadChildView();
        }
    };

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.adapter != null) {
            this.adapter.unregisterDataSetObserver(this.dataSetObserver);
        }

    }

    public FormView(Context context) {
        super(context);
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
    }

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
    }

    public void setAdapter(FormView.Adapter adapter) {
        if (this.adapter != adapter) {
            this.adapter = adapter;
            if (adapter != null) {
                adapter.registerDataSetObserver(this.dataSetObserver);
            }

            this.reloadChildView();
        }
    }

    private void reloadChildView() {
        this.removeAllViews();
        if (this.adapter != null) {
            for (int i = 0; i < this.adapter.getCount(); ++i) {
                View view = this.adapter.getView(i);
                if (view != null) {
                    this.addView(view);
                }
            }

            if (this.onItemClickListener != null) {
                this.setOnItemClickListener(this.onItemClickListener);
            }

            this.requestLayout();
        }
    }

    public void setOnItemClickListener(FormView.OnItemClickListener listener) {
        this.onItemClickListener = listener;

        for (int i = 0; i < this.adapter.getCount(); ++i) {
            if (this.adapter.getItemView(i).clickable) {
                final int finalI = i;
                this.adapter.getView(i).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        FormView.this.onItemClickListener.onItemClick(v, finalI);
                    }
                });
            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View var1, int var2);
    }

    public abstract static class ItemView {
        private View rootView;
        private boolean clickable = true;

        public ItemView(@NonNull Context context) {
            this.rootView = LayoutInflater.from(context).inflate(this.getLayoutId(), (ViewGroup) null);
        }

        public View getView() {
            return this.rootView;
        }

        public void setClickable(boolean flag) {
            this.clickable = flag;
        }

        public abstract int getLayoutId();
    }

    public static class Adapter {
        private final DataSetObservable dataSetObservable = new DataSetObservable();
        private final List<ItemView> list = new ArrayList();

        public Adapter() {
        }

        public void registerDataSetObserver(DataSetObserver observer) {
            this.dataSetObservable.registerObserver(observer);
        }

        public void unregisterDataSetObserver(DataSetObserver observer) {
            this.dataSetObservable.unregisterObserver(observer);
        }

        public void notifyDataSetChanged() {
            this.dataSetObservable.notifyChanged();
        }

        public View getView(int position) {
            return position < this.list.size() ? ((FormView.ItemView) this.list.get(position)).getView() : null;
        }

        public FormView.ItemView getItemView(int position) {
            return position < this.list.size() ? (FormView.ItemView) this.list.get(position) : null;
        }

        public void add(FormView.ItemView itemView) {
            this.list.add(itemView);
        }

        public void add(int position, FormView.ItemView itemView) {
            this.list.add(position, itemView);
        }

        public void remove(int position) {
            this.list.remove(position);
        }

        public void removeAll() {
            this.list.clear();
        }

        public int getCount() {
            return this.list.size();
        }
    }
}
