package com.zzmfaster.myapplication.http;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author yemao
 * @date 2017/4/9
 * @description 写自己的代码, 让别人说去吧!
 */

public abstract class BaseNewObserver<T> implements Observer<BaseRetData<T>> {

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(BaseRetData<T> retData) {
        if (retData.isSuccess()) {
            try {
                onSuccees(retData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(retData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e);
            } else {
                onFailure(e);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseRetData<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onCodeError(BaseRetData<T> t) throws Exception;

    /**
     * 返回失败
     *
     * @param e
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e) throws Exception;

    protected void onRequestStart() {
    }

}