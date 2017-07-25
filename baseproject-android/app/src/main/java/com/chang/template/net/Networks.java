package com.chang.template.net;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.chang.template.BuildConfig;
import com.chang.template.cache.GlobalConstant;
import com.chang.template.utils.AndroidUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Howard Chang on 2016/12/7
 */
public class Networks {

    private static Context context;

    private static Retrofit retrofit;
    private static Networks mNetworks;

    private static BackendAPI backendAPI;

    public static Networks getInstance(Context context) {
        if (mNetworks == null) {
            mNetworks = new Networks();
        }
        Networks.context = context;

        return mNetworks;
    }

    public BackendAPI getBackendAPI() {
        return backendAPI != null ? backendAPI : configRetrofit(BackendAPI.class);
    }

    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(GlobalConstant.BACKEND_API_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(service);
    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        Interceptor headerIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("X-Client-Platform", "Android");
                builder.addHeader("X-Client-Version", BuildConfig.VERSION_NAME);
                builder.addHeader("X-Client-Build", String.valueOf(BuildConfig.VERSION_CODE));
                builder.addHeader("Content-Type", "application/json");

                builder.addHeader("device_info", "Android_" + Build.VERSION.SDK_INT);
                builder.addHeader("app_ver", AndroidUtils.getVersionNameWithoutSuffix(context));

                Request request = builder.build();

                return chain.proceed(request);
            }
        };

        if (BuildConfig.DEBUG) {
            Interceptor loggingIntercept = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    ResponseBody responseBody = response.body();
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    Charset UTF8 = Charset.forName("UTF-8");
                    Log.i("REQUEST_JSON", buffer.clone().readString(UTF8));
                    Log.i("REQUEST_URL", request.toString());
                    return response;
                }
            };
            okHttpClient.addInterceptor(loggingIntercept);
        }

        okHttpClient.connectTimeout(GlobalConstant.WEB_SERVICES_TIMEOUT_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(GlobalConstant.WEB_SERVICES_TIMEOUT_SEC, TimeUnit.SECONDS);
        okHttpClient.addNetworkInterceptor(headerIntercept);

        return okHttpClient.build();
    }
}
