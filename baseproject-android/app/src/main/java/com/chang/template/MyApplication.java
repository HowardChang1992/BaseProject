package com.chang.template;

import android.content.Context;
import android.net.Uri;
import android.support.multidex.MultiDexApplication;


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

    }

}
