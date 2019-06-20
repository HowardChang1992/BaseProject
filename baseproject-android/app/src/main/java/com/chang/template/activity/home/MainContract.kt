package com.chang.template.activity.home

import android.content.Context

import com.chang.template.activity.BaseModel
import com.chang.template.activity.BasePresenter
import com.chang.template.activity.BaseView

/**
 * Created by Howard Chang on 2017/4/18
 */
interface MainContract {

    interface Model : BaseModel {

    }

    interface View : BaseView {

        fun initView(context: Context)

    }

    abstract class Presenter : BasePresenter() {

        abstract fun performRequest()

    }

}
