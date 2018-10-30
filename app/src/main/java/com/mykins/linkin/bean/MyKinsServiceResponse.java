package com.mykins.linkin.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jerry on 2017/8/26.
 */

public final class MyKinsServiceResponse<T> {
    private boolean error;

    @SerializedName("error_code")
    private int errorCode;

    private String message;

    private T data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
