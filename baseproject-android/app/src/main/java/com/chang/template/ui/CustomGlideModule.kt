package com.chang.template.ui

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

import java.io.InputStream

/**
 * Created by howard on 2018/1/31.
 */

class CustomGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val maxMemory = Runtime.getRuntime().maxMemory()
        val memoryCacheSize = maxMemory / 8

        builder.setMemoryCache(LruResourceCache(memoryCacheSize))
        builder.setBitmapPool(LruBitmapPool(memoryCacheSize))
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))

        super.applyOptions(context, builder)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java!!, InputStream::class.java!!, OkHttpUrlLoader.Factory())

        super.registerComponents(context, glide, registry)
    }
}
