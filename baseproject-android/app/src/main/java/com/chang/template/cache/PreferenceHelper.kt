package com.chang.template.cache

import android.content.Context
import android.content.SharedPreferences
import com.chang.template.net.BaseResponse

/**
 * Created by Howard Chang on 2016/1/4
 */
class PreferenceHelper(context: Context):BasePreference(context) {

    private val SYSTEM_PREFS = "system_prefs"
    private val APP_PREFS = "app_prefs"

    private val DEMO = "DEMO"

    var isDemoBoolean: Boolean
        get() {
            return getBooleanData(APP_PREFS, DEMO, false)
        }
        set(value) {
            setBooleanData(APP_PREFS, DEMO, value)
        }

}
