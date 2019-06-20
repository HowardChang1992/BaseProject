package com.chang.template.activity

import android.os.Bundle
import android.util.Log

/**
 * Created by Howard Chang on 2016/11/29
 */
abstract class BaseMVPFragment : BaseFragment(), BaseView {

    lateinit var basePresenter: BasePresenter

    abstract fun onBaseActivityCreated()

    open fun setPresenter(presenter: BasePresenter) {
        basePresenter = presenter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        basePresenter.onActivityCreated()
        onBaseActivityCreated()
    }

    override fun onDestroy() {
        basePresenter.onDestroy()
        super.onDestroy()
    }

}
