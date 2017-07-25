package com.chang.template.activity;

/**
 * Created by Howard Chang on 2016/11/29
 */
public interface BaseView {
    void onRequestStart();

    void onRequestError(String msg);

    void onRequestEnd();

    void onInternetError();

    void onRequestMessage(String msg);
}
