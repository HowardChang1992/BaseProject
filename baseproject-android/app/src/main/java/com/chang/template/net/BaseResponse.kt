package com.chang.template.net

/**
 * Created by Howard Chang on 2017/2/15
 */
open class BaseResponse<T> {

    val isShowMessage: Boolean = false
    private val error: Int? = null
    private val message: String? = null

    fun getError(): Int {
        return error ?: -1
    }

    fun getMessage(): String {
        return message ?: ""
    }
}
