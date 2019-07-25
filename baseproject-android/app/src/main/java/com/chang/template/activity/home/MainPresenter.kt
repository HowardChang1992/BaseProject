package com.chang.template.activity.home

import com.chang.template.activity.BaseActivity
import com.chang.template.activity.BaseMVPFragment

/**
 * Created by Howard Chang on 2017/4/18
 */
class MainPresenter(activity: BaseActivity
                    , var mView: MainContract.View
                    , var data: MainData
) : MainContract.Presenter() {

    private var mModel: MainModel

    init {
        this.activity = activity
        this.context = activity
        mModel = MainModel()
        mModel.mainData = data
        setView(mView as BaseMVPFragment, mModel)
    }

    override fun onActivityCreated() {

    }

    override fun performRequest() {

    }
}
