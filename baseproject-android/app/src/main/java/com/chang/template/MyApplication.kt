package com.chang.template

import android.content.Context
import android.support.multidex.MultiDexApplication

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemoryCache
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Howard Chang on 2016/11/29
 */
class MyApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
    }


}
