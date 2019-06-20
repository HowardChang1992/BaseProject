package com.chang.template.cache


import com.chang.template.BuildConfig

/**
 * Created by Howard Chang on 2016/11/2
 */
object GlobalConstant {


    var WEB_SERVICES_TIMEOUT_SEC: Long = 0
    private val WEB_SERVICES_TIMEOUT_FORMAL_SEC: Long = 20
    private val WEB_SERVICES_TIMEOUT_DEV_SEC: Long = 60

    var BACKEND_API_URL: String
    private val BACKEND_API_FORMAL_URL = ""
    private val BACKEND_API_DEV_URL = ""


    init {
        if (BuildConfig.BUILD_TYPE == "dev") {
            BACKEND_API_URL = BACKEND_API_DEV_URL
            WEB_SERVICES_TIMEOUT_SEC = WEB_SERVICES_TIMEOUT_DEV_SEC
        } else {
            BACKEND_API_URL = BACKEND_API_FORMAL_URL
            WEB_SERVICES_TIMEOUT_SEC = WEB_SERVICES_TIMEOUT_FORMAL_SEC
        }
    }
}
