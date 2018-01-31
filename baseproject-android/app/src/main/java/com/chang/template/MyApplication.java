package com.chang.template;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Howard Chang on 2016/11/29
 */
public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        initGlide(this);
    }

    public static void initGlide(Context context) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long memoryCacheSize = maxMemory / 8;

        GlideBuilder glideBuilder = new GlideBuilder();
        glideBuilder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        glideBuilder.setBitmapPool(new LruBitmapPool(memoryCacheSize));

        glideBuilder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));

        Glide.init(context, glideBuilder);
    }

}
