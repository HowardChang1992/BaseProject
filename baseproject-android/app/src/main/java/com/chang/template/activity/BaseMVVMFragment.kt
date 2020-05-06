package com.chang.template.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chang.template.utils.observeWith

abstract class BaseMVVMFragment<T : ViewDataBinding> : BaseFragment() {

    abstract val viewModel: BaseViewModel
    abstract var binding: T

    abstract fun bindingViewModel()

    abstract fun initView()

    abstract fun initViewModelObservers()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentView, container, false)
        binding.lifecycleOwner = this

        bindingViewModel()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        initViewModelObservers()

        viewModel.isShowLoadingProgress.observeWith(this) {
            if (it) {
                onRequestStart()
            } else {
                onRequestEnd()
            }
        }
    }

}