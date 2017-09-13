package com.chang.template.activity.home;

import android.content.Context;

import com.chang.template.activity.BaseImplementModel;
import com.chang.template.net.Networks;
import com.chang.template.net.model.DemoResponse;

import io.reactivex.Observable;

/**
 * Created by Howard Chang on 2017/4/18
 */
public class MainModel extends BaseImplementModel implements MainContract.Model {

    @Override
    public Observable<DemoResponse> performRequest(Context context) {

        return Networks.getInstance(context).getBackendAPI().baseApi();

    }

}
