package com.chang.template.activity


import android.content.Context
import com.chang.template.activity.rx.RxManager


/**
 * Created by Howard Chang on 2016/11/29
 */
abstract class BasePresenter {

    lateinit var context: Context
    lateinit var activity: BaseActivity
    lateinit var baseMVPFragment: BaseMVPFragment
    lateinit var baseModel: BaseModel

    var mRxManager = RxManager()

    init {

    }

    open fun onActivityCreated() {

    }

    protected fun setView(view: BaseMVPFragment, model: BaseModel) {
        baseMVPFragment = view
        baseModel = model

        baseMVPFragment.setPresenter(this)
    }

    fun onDestroy() {
        mRxManager.clear()
    }

}
