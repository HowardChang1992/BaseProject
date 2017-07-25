package com.chang.template;

import android.content.Context;
import android.net.Uri;
import android.support.multidex.MultiDexApplication;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Howard Chang on 2016/11/29
 */
public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        buildPicasso(this);
    }

    public static void buildPicasso(Context context) {
        Picasso.Builder builder = new Picasso.Builder(context);

        LruCache cache = new LruCache(5 * 1024 * 1024);
        builder.memoryCache(cache);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        builder.executor(executorService);

        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });

        Picasso picasso = builder.build();
        Picasso.setSingletonInstance(picasso);
    }

}
