package com.chang.template

import com.chang.template.activity.home.MainViewModel
import com.chang.template.cache.MemberHelper
import com.chang.template.cache.PreferenceHelper
import com.chang.template.net.Networks
import org.koin.android.ext.koin.androidContext

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val mModule = module {
        viewModel { MainViewModel() }

        single { (get() as Networks).getBackendAPIRepository() }

        single { MemberHelper(androidContext()) }
        single { PreferenceHelper(androidContext()) }
        single { Networks.getInstance(androidContext()) }
    }

}