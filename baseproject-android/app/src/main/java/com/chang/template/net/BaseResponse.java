package com.chang.template.net;

/**
 * Created by Howard Chang on 2017/2/15
 */
public class BaseResponse<T> {

    private boolean show_message;
    private Integer error;
    private String message;
    protected T data;

    public void setData(T data) {
        this.data = data;
    }

    public int getError() {
        return error != null ? error : -1;
    }

    public String getMessage() {
        return message != null ? message : "";
    }

    public boolean isShowMessage() {
        return show_message;
    }

    public T getData() {
        return data;
    }
}
