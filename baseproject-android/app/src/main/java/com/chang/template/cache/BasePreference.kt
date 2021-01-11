package com.chang.template.cache

import android.content.Context
import com.google.gson.Gson

/**
 * Created by Howard Chang on 2016/1/4
 */
open class BasePreference(private val context: Context) {


    protected fun getStringData(level: String, key: String): String? {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getString(key, "")
    }

    protected fun setStringData(level: String, key: String, value: String) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putString(key, value)
        editor.apply()
    }

    protected fun getBooleanData(level: String, key: String, default: Boolean): Boolean {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getBoolean(key, default)
    }

    protected fun setBooleanData(level: String, key: String, value: Boolean) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    protected fun getLongData(level: String, key: String): Long {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getLong(key, -1L)
    }

    protected fun setLongData(level: String, key: String, value: Long) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putLong(key, value)
        editor.apply()
    }

    protected fun getIntData(level: String, key: String, defaultValue: Int): Int {
        return context.getSharedPreferences(level, Context.MODE_PRIVATE).getInt(key, defaultValue)
    }

    protected fun setIntData(level: String, key: String, value: Int) {
        val editor = context.getSharedPreferences(level, Context.MODE_PRIVATE).edit()
        editor.putInt(key, value)
        editor.apply()
    }

}
