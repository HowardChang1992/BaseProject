package com.chang.template.activity;


import android.content.Context;

import com.chang.template.R;
import com.chang.template.activity.rx.RxManager;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.lang.reflect.Field;

import retrofit2.Response;


/**
 * Created by Howard Chang on 2016/11/29
 */
public abstract class BasePresenter {

    public Context mContext;
    public BaseMVPFragment mView;
    public BaseModel mModel;

    public RxManager mRxManager = new RxManager();

    protected Context context;

    public void onActivityCreated(){

    }

    protected void setView(BaseMVPFragment view, BaseModel model) {
        mView = view;
        mModel = model;
    }

    public void onDestroy() {
        mRxManager.clear();
    }

    protected <T> boolean isSuccess(boolean isShowMessage, T response, Object... enums) {
        try {
            Field status = response.getClass().getDeclaredField("status");
            status.setAccessible(true);
            for (Object successEnum : enums) {
                if (status.get(response).equals(successEnum)) {
                    return true;
                }
            }
        } catch (Exception e) {

        }
        if (isShowMessage) {
            mView.onRequestMessage(getMessage(response));
        }
        return false;
    }

    protected <T> T getResponse(Throwable throwable, Class<T> t) {
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            Response response = exception.response();
            try {
                return new Gson().fromJson(response.errorBody().string(), t);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    protected <T> String getMessage(T response) {
        try {
            Field message = response.getClass().getDeclaredField("message");
            message.setAccessible(true);
            return (String) message.get(response);
        } catch (Exception e) {
            return context.getString(R.string.g_a_network_error);
        }
    }

    protected <T> boolean checkStatus(T response) {
        try {
            Field status = response.getClass().getDeclaredField("status");
            status.setAccessible(true);
            switch (status.get(response).toString()) {

                default:
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }


}
