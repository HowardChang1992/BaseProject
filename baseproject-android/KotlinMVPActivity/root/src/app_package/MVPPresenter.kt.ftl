package ${packageName}

import ${applicationPackage}.activity.BaseActivity
import ${applicationPackage}.activity.BaseMVPFragment

class ${presenterClass}(activity: BaseActivity
                    ,var mView: ${contractClass}.View
                    ,var data: ${dataClass}) : ${contractClass}.Presenter() {

    private var mModel: ${modelClass}

    init {
        this.activity = activity
        this.context = activity
        mModel = ${modelClass}()
        mModel.${dataParameter} = data
        setView(mView as BaseMVPFragment, mModel)
    }

    override fun onActivityCreated() {

    }

    override fun performRequest() {

    }
}
