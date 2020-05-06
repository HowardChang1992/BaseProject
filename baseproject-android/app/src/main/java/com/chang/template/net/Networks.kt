package com.chang.template.net

import android.content.Context
import android.os.Build
import android.util.Log
import com.chang.template.BuildConfig
import com.chang.template.cache.GlobalConstant
import com.chang.template.utils.AndroidUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Created by Howard Chang on 2016/12/7
 */
class Networks {

    private var backendApiRepository: BackendApiRepository? = null

    fun getBackendAPIRepository(): BackendApiRepository {
        return backendApiRepository ?: configRetrofit(BackendApiRepository::class.java)
    }

    private fun <T : Any> configRetrofit(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(GlobalConstant.BACKEND_API_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(service)
    }

    private fun configClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        val headerIntercept = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("X-Client-Platform", "Android")
            builder.addHeader("X-Client-Version", BuildConfig.VERSION_NAME)
            builder.addHeader("X-Client-Build", BuildConfig.VERSION_CODE.toString())
            builder.addHeader("Content-Type", "application/json")

            builder.addHeader("device_info", "Android_" + Build.VERSION.SDK_INT)
            builder.addHeader("app_ver", AndroidUtils.getVersionNameWithoutSuffix(context))

            val request = builder.build()

            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            val loggingIntercept = Interceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                val responseBody = response.body
                val source = responseBody?.source()
                source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source?.buffer
                val UTF8 = Charset.forName("UTF-8")

                var requestJson = ""
                buffer?.apply {
                    requestJson = this.clone().readString(UTF8)
                }

                Log.i("REQUEST_JSON", requestJson)
                Log.i("REQUEST_URL", request.toString())
                response
            }
            okHttpClient.addInterceptor(loggingIntercept)
        }

        okHttpClient.connectTimeout(GlobalConstant.WEB_SERVICES_TIMEOUT_SEC, TimeUnit.SECONDS)
        okHttpClient.readTimeout(GlobalConstant.WEB_SERVICES_TIMEOUT_SEC, TimeUnit.SECONDS)
        okHttpClient.addNetworkInterceptor(headerIntercept)

        return okHttpClient.build()
    }

    companion object {

        @Volatile
        private var mNetworks: Networks? = null
        private lateinit var context: Context

        fun getInstance(mContext: Context) =
                mNetworks ?: synchronized(Networks::class.java) {
                    mNetworks.apply {
                        context = mContext
                    } ?: Networks().apply {
                        context = mContext
                    }
                }

    }
}
