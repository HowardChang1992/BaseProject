package com.chang.template.net

import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Created by Howard on 2016/7/14
 */
interface BackendAPI {

    @POST("demo/api")
    fun baseApi(): Observable<Any>

}
