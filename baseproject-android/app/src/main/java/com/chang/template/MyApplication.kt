package com.chang.template

import androidx.multidex.MultiDexApplication

import com.chang.template.AppModule.mModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Howard Chang on 2016/11/29
 */
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // modules
            modules(mModule)
        }
    }

}
