package ${packageName}

import android.content.Context

import ${applicationPackage}.activity.BaseModel
import ${applicationPackage}.activity.BasePresenter
import ${applicationPackage}.activity.BaseView

interface ${contractClass} {

    interface Model : BaseModel {

         var ${dataParameter}: ${dataClass}?

    }

    interface View : BaseView {

        fun initView(context: Context)

    }

    abstract class Presenter : BasePresenter() {

        abstract fun performRequest()

    }

}
