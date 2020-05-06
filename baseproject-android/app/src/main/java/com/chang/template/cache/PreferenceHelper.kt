package com.chang.template.cache

import android.content.Context
import android.content.SharedPreferences
import com.chang.template.net.BaseResponse

/**
 * Created by Howard Chang on 2016/1/4
 */
class PreferenceHelper(private val context: Context) {

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

    private fun getStringData(level: String, key: String): String? {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getString(key, "")
    }

    private fun setStringData(level: String, key: String, value: String) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getBooleanData(level: String, key: String, default: Boolean): Boolean {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getBoolean(key, default)
    }

    private fun setBooleanData(level: String, key: String, value: Boolean) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getLongData(level: String, key: String): Long {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getLong(key, -1L)
    }

    private fun setLongData(level: String, key: String, value: Long) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putLong(key, value)
        editor.apply()
    }

    private fun getIntData(level: String, key: String, defaultValue: Int): Int {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getInt(key, defaultValue)
    }

    private fun setIntData(level: String, key: String, value: Int) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putInt(key, value)
        editor.apply()
    }

}
