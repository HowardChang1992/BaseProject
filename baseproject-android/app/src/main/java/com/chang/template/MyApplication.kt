package com.chang.template

import androidx.multidex.MultiDexApplication

import com.chang.template.AppModule.mModule
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Howard Chang on 2016/11/29
 */
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.FLAVOR.toLowerCase() != "publish") {
            Stetho.initializeWithDefaults(this)
        } else {
            Fabric.with(this, Crashlytics());
        }

        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // modules
            modules(mModule)
        }
    }

}
