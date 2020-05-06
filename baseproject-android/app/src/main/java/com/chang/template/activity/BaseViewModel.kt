package com.chang.template.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Serializable


abstract class BaseViewModel : ViewModel() {

    var isShowLoadingProgress = MutableLiveData<Boolean>()
    var initData = MutableLiveData<Serializable>()

    fun initData(serializable: Serializable?) {
        initData.value = serializable as Serializable
    }

}