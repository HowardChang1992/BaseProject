package com.chang.template.net;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by Howard on 2016/7/14
 */
public interface BackendAPI {

    @POST("demo/api")
    Observable<Object> baseApi();

}
