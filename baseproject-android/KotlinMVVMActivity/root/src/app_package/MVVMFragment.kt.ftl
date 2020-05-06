package ${packageName}

import android.content.Context
import android.os.Bundle
import ${applicationPackage}.R
import ${applicationPackage}.activity.BaseMVVMFragment
import ${applicationPackage}.databinding.${fragmentBinding}
import org.koin.androidx.viewmodel.ext.android.viewModel

class ${fragmentClass} : BaseMVVMFragment<${fragmentBinding}>() {

    override lateinit var binding: ${fragmentBinding}
    override val viewModel: ${viewModelClass} by viewModel()

    override val contentView: Int
        get() = R.layout.${fragmentView}

    override fun bindingViewModel() {
        binding.viewModel = viewModel
        viewModel.initData(arguments?.getSerializable(DATA))
    }

    override fun initView() {

    }

    override fun initViewModelObservers() {

    }

    companion object {
        fun newInstance(data: ${dataClass}) =
                ${fragmentClass}().apply {
                    arguments = Bundle().apply {
                        putSerializable(DATA, data)
                    }
                }
    }
}