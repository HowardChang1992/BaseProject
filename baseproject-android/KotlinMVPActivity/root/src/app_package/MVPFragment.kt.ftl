package ${packageName}

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable

import ${applicationPackage}.R
import ${applicationPackage}.activity.BaseMVPFragment
import ${applicationPackage}.activity.BasePresenter


class ${fragmentClass} : BaseMVPFragment(), ${contractClass}.View {

    private lateinit var mPresenter: ${contractClass}.Presenter

    override val contentView: Int
        get() = R.layout.${fragmentView}

    override fun setPresenter(presenter: BasePresenter) {
        super.setPresenter(presenter)
        mPresenter = presenter as ${contractClass}.Presenter
    }

    override fun onBaseActivityCreated() {

    }

    override fun initView(context: Context) {

    }

    companion object {

        fun newInstance(): ${fragmentClass} {
            return ${fragmentClass}()
        }
    }

}