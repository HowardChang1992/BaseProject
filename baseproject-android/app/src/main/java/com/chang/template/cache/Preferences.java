package com.chang.template.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Howard Chang on 2016/1/4
 */
public class Preferences {

    private static final String SYSTEM_PREFS = "system_prefs";
    private static final String APP_PREFS = "app_prefs";

    private static final String DEMO = "DEMO";


    public static void setDemo(Context context, boolean demo) {
        SharedPreferences.Editor editor = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).edit();
        editor.putBoolean(DEMO, demo);
        editor.apply();
    }

    public static boolean getDemo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return prefs.getBoolean(DEMO, true);
    }



}
