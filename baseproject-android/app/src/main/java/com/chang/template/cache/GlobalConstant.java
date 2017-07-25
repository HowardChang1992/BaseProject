package com.chang.template.cache;


import com.chang.template.BuildConfig;

/**
 * Created by Howard Chang on 2016/11/2
 */
public class GlobalConstant {


    public static long WEB_SERVICES_TIMEOUT_SEC;
    private static final long WEB_SERVICES_TIMEOUT_FORMAL_SEC = 20;
    private static final long WEB_SERVICES_TIMEOUT_DEV_SEC = 60;

    public static String BACKEND_API_URL;
    private static String BACKEND_API_FORMAL_URL = "";
    private static String BACKEND_API_DEV_URL = "";


    static {
        if (BuildConfig.BUILD_TYPE.equals("dev")) {
            BACKEND_API_URL = BACKEND_API_DEV_URL;
            WEB_SERVICES_TIMEOUT_SEC = WEB_SERVICES_TIMEOUT_DEV_SEC;
        } else {
            BACKEND_API_URL = BACKEND_API_FORMAL_URL;
            WEB_SERVICES_TIMEOUT_SEC = WEB_SERVICES_TIMEOUT_FORMAL_SEC;
        }
    }
}
