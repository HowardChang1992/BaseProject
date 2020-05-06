package com.chang.template.activity.home

import android.os.Bundle
import com.chang.template.R
import com.chang.template.activity.BaseMVVMFragment
import com.chang.template.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Howard Chang on 2017/4/18
 */
class MainFragment : BaseMVVMFragment<FragmentMainBinding>() {

    override lateinit var binding: FragmentMainBinding
    override val viewModel: MainViewModel by viewModel()

    override val contentView: Int
        get() = R.layout.fragment_main

    override fun bindingViewModel() {
        binding.viewModel = viewModel
        viewModel.initData(arguments?.getSerializable(DATA))
    }

    override fun initView() {

    }

    override fun initViewModelObservers() {

    }

    companion object {
        fun newInstance(data: MainData) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(DATA, data)
                    }
                }
    }

}
