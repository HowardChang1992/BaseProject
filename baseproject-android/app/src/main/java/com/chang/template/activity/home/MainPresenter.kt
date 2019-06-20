package com.chang.template.activity.home

import android.util.Log
import com.chang.template.activity.BaseActivity
import com.chang.template.activity.BaseMVPFragment
import com.chang.template.activity.rx.RxBus
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Howard Chang on 2017/4/18
 */
class MainPresenter(activity: BaseActivity
                    , mView: MainContract.View) : MainContract.Presenter() {

    private var mModel: MainModel

    init {
        this.activity = activity
        this.context = activity
        mModel = MainModel()
        setView(mView as BaseMVPFragment, mModel)
    }

    override fun onActivityCreated() {

    }

    override fun performRequest() {

    }
}
