package com.zzmfaster.myapplication.http;

public class BaseRetData<T> {
    private String message;    //信息
    private int code;          //返回请求码
    private T data;
    private int status;        //请求状态
    private static int SUCCESS_CODE = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return getCode()==SUCCESS_CODE;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
