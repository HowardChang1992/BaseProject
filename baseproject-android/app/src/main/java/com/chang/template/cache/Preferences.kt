package com.chang.template.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Howard Chang on 2016/1/4
 */
object Preferences {

    private val SYSTEM_PREFS = "system_prefs"
    private val APP_PREFS = "app_prefs"

    private val DEMO = "DEMO"


    fun setDemo(context: Context, demo: Boolean) {
        val editor = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).edit()
        editor.putBoolean(DEMO, demo)
        editor.apply()
    }

    fun getDemo(context: Context): Boolean {
        val prefs = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
        return prefs.getBoolean(DEMO, true)
    }


}
