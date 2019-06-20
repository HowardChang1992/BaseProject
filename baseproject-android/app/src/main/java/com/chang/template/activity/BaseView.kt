package com.chang.template.activity

/**
 * Created by Howard Chang on 2016/11/29
 */
interface BaseView{
    fun onRequestStart()

    fun onRequestError(msg: String)

    fun onRequestEnd()

    fun onInternetError()

    fun onRequestMessage(msg: String)
}
