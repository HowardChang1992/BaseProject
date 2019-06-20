package com.chang.template.activity.home

import android.content.Context
import android.util.Log
import android.widget.TextView
import butterknife.BindView

import com.chang.template.R
import com.chang.template.activity.BaseMVPFragment
import com.chang.template.activity.BasePresenter

/**
 * Created by Howard Chang on 2017/4/18
 */
class MainFragment : BaseMVPFragment(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter

    override val contentView: Int
        get() = R.layout.fragment_main

    override fun setPresenter(presenter: BasePresenter) {
        super.setPresenter(presenter)
        mPresenter = presenter as MainContract.Presenter
    }

    override fun onBaseActivityCreated() {

    }

    override fun initView(context: Context) {

    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}
