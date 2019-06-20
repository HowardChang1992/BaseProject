package ${packageName};

import ${applicationPackage}.activity.BaseActivity;
import ${applicationPackage}.activity.BaseMVPFragment;

class ${presenterClass}(activity: BaseActivity
                    , mView: ${contractClass}.View) : ${contractClass}.Presenter() {

    private var mModel: ${modelClass}

    init {
        this.activity = activity
        this.context = activity
        mModel = ${modelClass}()
        setView(mView as BaseMVPFragment, mModel)
    }

    override fun onActivityCreated() {

    }

    override fun performRequest() {

    }
}
