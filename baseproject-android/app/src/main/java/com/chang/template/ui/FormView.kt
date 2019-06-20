package com.chang.template.ui

import android.content.Context
import android.database.DataSetObservable
import android.database.DataSetObserver
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import java.util.*

/**
 * Created by Howard Chang on 2016/11/2
 */
class FormView : LinearLayout {
    private var onItemClickListener: FormView.OnItemClickListener? = null
    private var adapter: FormView.Adapter? = null

    private val dataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            this@FormView.reloadChildView()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (this.adapter != null) {
            this.adapter!!.unregisterDataSetObserver(this.dataSetObserver)
        }

    }

    constructor(context: Context) : super(context) {
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
    }

    fun setAdapter(adapter: FormView.Adapter?) {
        if (this.adapter !== adapter) {
            this.adapter = adapter
            adapter?.registerDataSetObserver(this.dataSetObserver)

            this.reloadChildView()
        }
    }

    private fun reloadChildView() {
        this.removeAllViews()
        if (this.adapter != null) {
            for (i in 0 until this.adapter!!.count) {
                val view = this.adapter!!.getView(i)
                if (view != null) {
                    this.addView(view)
                }
            }

            if (this.onItemClickListener != null) {
                this.setOnItemClickListener(this.onItemClickListener!!)
            }

            this.requestLayout()
        }
    }

    fun setOnItemClickListener(listener: FormView.OnItemClickListener) {
        this.onItemClickListener = listener

        for (i in 0 until this.adapter!!.count) {
            if (this.adapter!!.getItemView(i)!!.mClickable) {
                this.adapter!!.getView(i)!!.setOnClickListener { v -> this@FormView.onItemClickListener!!.onItemClick(v, i) }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(var1: View, var2: Int)
    }

    abstract class ItemView(context: Context) {
        val view: View
        var mClickable = true

        abstract val layoutId: Int

        init {
            this.view = LayoutInflater.from(context).inflate(this.layoutId, null as ViewGroup?)
        }

        fun setClickable(flag: Boolean) {
            this.mClickable = flag
        }
    }

    class Adapter {
        private val dataSetObservable = DataSetObservable()
        private val list = ArrayList<ItemView>()

        val count: Int
            get() = this.list.size

        fun registerDataSetObserver(observer: DataSetObserver) {
            this.dataSetObservable.registerObserver(observer)
        }

        fun unregisterDataSetObserver(observer: DataSetObserver) {
            this.dataSetObservable.unregisterObserver(observer)
        }

        fun notifyDataSetChanged() {
            this.dataSetObservable.notifyChanged()
        }

        fun getView(position: Int): View? {
            return if (position < this.list.size) (this.list.get(position) as ItemView).view else null
        }

        fun getItemView(position: Int): ItemView? {
            return if (position < this.list.size) this.list.get(position) else null
        }

        fun add(itemView: ItemView) {
            this.list.add(itemView)
        }

        fun add(position: Int, itemView: ItemView) {
            this.list.add(position, itemView)
        }

        fun remove(position: Int) {
            this.list.removeAt(position)
        }

        fun removeAll() {
            this.list.clear()
        }
    }
}
